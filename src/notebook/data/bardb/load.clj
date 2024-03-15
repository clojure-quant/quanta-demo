(ns notebook.data.bardb.load
  (:require
   [tick.core :as t]
   [tablecloth.api :as tc]
   [ta.db.bars.protocol :as b]
   [ta.db.bars.nippy :as nippy]
   [modular.system]))


(def db (nippy/start-bardb-nippy "output/kibot-intraday/"))


(def window {:start (t/instant "2022-03-05T00:00:00Z")
             :end (t/instant "2024-03-20T20:00:00Z")})

(b/get-bars db
            {:asset "JY0"  ; "USD/JPY"
             :calendar [:forex :m]}
            window)

(-> (b/get-bars db
                {:asset "QQQ"  ; "USD/JPY"
                 :calendar [:forex :m]}
                window)
 
 )


; 2024-03-15T01:59  forex
; 2024-03-15T03:22  futures
; 2024-03-15T11:46 etf