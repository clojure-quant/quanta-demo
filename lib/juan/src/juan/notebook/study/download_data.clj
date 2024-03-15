(ns juan.notebook.study.download-data
  (:require
   [taoensso.timbre :refer [info warn error]]
   [tick.core :as t]
   [de.otto.nom.core :as nom]
   [tablecloth.api :as tc]
   [ta.db.asset.db :as db]
   [ta.calendar.core :as cal]
   [ta.db.bars.protocol :as b]
   [ta.db.bars.nippy :as nippy]
   [ta.db.bars.duckdb :as duck]
   [ta.import.helper.retries :refer [with-retries]]
   [modular.system]
   [juan.asset-pairs :refer [asset-pairs]]))

; intraday data is big, so we preload it.
; this ensures that our historical study runs faster.

(def db-duck (modular.system/system :duckdb))
(def db-dyn (modular.system/system :bardb-dynamic))
(def db-nippy (nippy/start-bardb-nippy "output/kibot-intraday/"))
(def im (modular.system/system :import-manager))

(cal/trailing-range [:forex :d] 5000)

(def window-daily
  (cal/trailing-range [:forex :d] 1000 (t/zoned-date-time "2024-03-07T17:30-05:00[America/New_York]")))

window-daily

(defn get-forex-daily [asset]
  (info "getting intraday forex for: " asset)
  (with-retries 5 b/get-bars db-dyn {:asset asset
                                 :calendar [:forex :d]
                                 :import :kibot}
    window-daily))

(def window-intraday
  (cal/trailing-range [:forex :m] 90000 (t/zoned-date-time "2024-03-08T20:00-05:00[America/New_York]")))

window-intraday

(defn get-forex-intraday [asset]
  (info "getting intraday forex for: " asset)
  (let [opts {:asset asset
              :calendar [:forex :m]
              :import :kibot-http}
        ds (with-retries 5 b/get-bars im opts
             {:start (t/instant "2019-12-01T00:00:00Z")
              :end (t/instant "2020-02-01T00:00:00Z")})]
    (if (nom/anomaly? ds)
      (do
        (error "could not get asset: " asset)
        {:asset asset :count 0})
      (let [c (tc/row-count ds)]
        (info "recevied from kibot asset:" asset " count: " c)
        (b/append-bars db-nippy opts ds)
        (duck/delete-bars db-duck [:forex :m] asset)
        (b/append-bars db-duck opts ds)
        {:asset asset :count c}))))

;; import one forex
(db/instrument-details "EUR/USD")

(get-forex-daily "EUR/USD")
(get-forex-intraday "EUR/USD")

;; import all forex bars

asset-pairs

(doall
 (for [pair asset-pairs]
   (get-forex-daily (:fx pair))))

(doall
 (for [pair asset-pairs]
   (do 
     (get-forex-intraday (:fx pair))
     (get-forex-intraday (str (:future pair) "0"))
     )
   ))


