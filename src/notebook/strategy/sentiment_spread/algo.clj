(ns notebook.strategy.sentiment-spread.algo
  (:require
   [taoensso.timbre :refer [trace debug info warn error]]
   [tick.core :as t]
   [tablecloth.api :as tc]
   [tech.v3.datatype :as dtype]
   [tech.v3.datatype.functional :as fun]
   [ta.algo.env.core :refer [get-bars-aligned-filled]]
   [ta.calendar.core :as cal]
   [ta.indicator.returns :refer [diff diff-n]]))

(defn calc-asset-change [env {:keys [calendar asset import] :as spec} cal-seq]
  (info "calculating asset " asset "on calendar:" calendar "import: " import)
  (try (let [bars (get-bars-aligned-filled env spec cal-seq)
             close (:close bars)
             ;close-diff (diff close)
             close-diff (diff-n 10 close)]
         ;(info "close-diff for asst: " asset "diff: " close-diff)
         close-diff)
       (catch Exception ex
         (error "could not calculate asset: " asset)
         nil)))

(defn calc-spread [env {:keys [calendar import]} cal-seq spread]
  (let [[spread-name asset-a asset-b] spread
        _ (info "calculating spread: " spread-name asset-a asset-b)
        a-chg (calc-asset-change env {:calendar calendar :asset asset-a :import import} cal-seq)
        b-chg (calc-asset-change env {:calendar calendar :asset asset-b :import import} cal-seq)
        diff-chg (when (and a-chg b-chg)
                   (fun/- a-chg b-chg))]
    [spread-name diff-chg]))


(defn assets [spreads]
  (set (sort (mapcat rest spreads))))


(defn pos-1 [n]
  (if (>= n 0) 1.0 0.0))

(defn neg-1 [n]
  (if (< n 0) 1.0 0.0))

(defn add-counts [ds-sentiment spread-names]
  (let [pos? (fn [p spread]
               (->> (get ds-sentiment spread)
                    (dtype/emap p :double)))
        pos (apply fun/+ (map #(pos? pos-1 %) spread-names))
        neg (apply fun/+ (map #(pos? neg-1 %) spread-names))
        sentiment (fun/- pos neg)]
    (tc/add-columns ds-sentiment {:pos pos :neg neg :sentiment sentiment})))


(defn sentiment-spread
  "for a point in time, does calculation of all spreads on a trailing-n window.
   all spreads that can be calculated will be added as columns."
  [env {:keys [calendar trailing-n spreads] :as spec} time]
  (info "calculating sentiment-spreads ending: " time)
  (let [cal-seq (cal/trailing-window calendar trailing-n time)
        bars (get-bars-aligned-filled env (assoc spec :asset (:market spec)) cal-seq)
        date-col (reverse (map t/instant cal-seq))
        assets (assets spreads) ; for live env, we somehow have to add realtime-data to bar-generator for all assets
        ok? (fn [[_spread-name diff]]
              diff)
        spreads (map #(calc-spread env spec cal-seq %) spreads)
        spreads-ok (filter ok? spreads)
        spread-names-ok (map first spreads-ok)
        spreads-ds (->> spreads-ok
                        (filter ok?)
                        (into {})
                        (tc/dataset))]
    (info "assets: " assets)
    (-> spreads-ds
        (tc/add-column :date date-col)
        (add-counts spread-names-ok)
        (tc/add-column :market (:close bars))
        (tc/select-rows (range 15 (tc/row-count spreads-ds))))))