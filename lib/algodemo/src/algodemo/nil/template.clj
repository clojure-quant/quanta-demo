(ns algodemo.nil.template
  (:require
   [ta.db.asset.db :as db]))

(defn nil-algo [_env opts bar-ds]
  bar-ds)

(defn all-cryptos []
  (->> (db/symbols-available :crypto)
       (into [])))

(def watch-crypto
  {:id :watch-crypto
   :algo {:type :trailing-bar
          :trailing-n 300
          :calendar [:crypto :m]
          :asset "BTCUSDT"
          :import :bybit
          :feed :bybit
          :algo 'notebook.strategy.live.crypto/nil-algo
          ; irrelevant parameter; just ui demo.
          :dummy "just some text"
          :super-super-fast? true}
   :options (fn []
              [{:type :select
                :path :asset
                :name "Asset"
                :spec (all-cryptos) ; ["BTCUSDT" "ETHUSDT"]
                }
               {:type :select
                :path :trailing-n
                :name "trailing-n"
                :spec [100 300 500 1000 2000 3000 5000 10000]}
               {:type :string
                :path :dummy
                :name "dummy-text"}
               {:type :bool
                :path :super-super-fast?
                :name "SuperSuperFast?"}])
   :chart {:viz 'ta.viz.ds.highchart/highstock-render-spec
           :viz-options {:chart {:box :fl}
                         :charts [{:close :candlestick #_:ohlc}
                                  {:volume {:type :column :color "red"}}]}}})

