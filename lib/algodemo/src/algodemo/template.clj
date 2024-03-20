(ns algodemo.template
  (:require
   [ta.db.asset.db :as db]
   ; vega specs here:
   [algodemo.sentiment-spread.vega]
   [algodemo.asset-compare.viz]))

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

(def sma-crypto
  {:id :sma-crypto
   :algo {:type :trailing-bar
          :calendar [:forex :m]
          :trailing-n 1000
          :asset "ETHUSDT"
          :feed :bybit
          :import :bybit
          :algo 'algodemo.sma-crossover.algo/sma-crossover-algo
          :sma-length-st 20
          :sma-length-lt 200}
   :options [{:type :select
              :path :asset
              :name "Asset"
              :spec ["BTCUSDT" "ETHUSDT"]}
             {:type :select
              :path :trailing-n
              :name "trailing-n"
              :spec [100 300 500 1000 2000 3000 5000 10000]}
             {:type :select
              :path :sma-length-st
              :name "sma-st"
              :spec [10 20 50 100]}
             {:type :select
              :path :sma-length-lt
              :name "sma-lt"
              :spec [100 200 500 1000]}]
   :chart {:viz 'ta.viz.ds.highchart/highstock-render-spec
           :viz-options {:chart {:box :fl}
                         :charts [{:close :candlestick ; :ohlc ; :line 
                                   :sma-lt :line
                                   :sma-st :line}
                                  {:volume :column}]}}
   :metrics {:viz 'ta.viz.ds.metrics/metrics-render-spec
             :viz-options {}}})

(def sentiment-spread
  {:id :sentiment-spread
   :algo {:type :time
          :calendar [:us :d]
          :algo 'algodemo.sentiment-spread.algo/sentiment-spread
          :import :kibot
          :trailing-n 1000
          :market "SPY"
          :spreads [[:consumer-sentiment "XLY" "XLP"]
                    [:smallcap-speculative-demand "IWM" "SPY"]
                    [:em-speculative-demand "EEM" "EFA"]
                    [:innovation-vs-safehaven "XLK" "GLD"]
                    [:stocks-vs-bonds "SPY" "AGG"]
                    [:quality-yield-spreads "HYG" "AGG"]
                    [:yen-eur-currency "FXE" "FXY"]
           ; 8th spread- VXX-VXZ – due to insufficient historical data.
                    ]}
   :options [{:type :select
              :path :market
              :name "Market"
              :spec ["SPY" "QQQ" "IWM"]}]
   :chart {:viz 'ta.viz.ds.vega/vega-render-spec
           :viz-options algodemo.sentiment-spread.vega/vega-spec}})

(def asset-compare
  {:id :asset-compare
   :algo {:type :time
          :calendar [:us :d]
          :algo 'algodemo.asset-compare.algo/asset-compare-algo
          :assets ["GLD" "UUP" "SPY" "QQQ" "IWM" "EEM" "EFA" "IYR" "USO" "TLT"]
          :import :kibot
          :trailing-n 1000}
   :chart {:viz 'ta.viz.ds.vega/vega-render-spec
           :viz-options algodemo.asset-compare.viz/vega-spec}})

(def reversal-and-breakout
  {:id :reversal-and-breakout
   :algo {:type :trailing-bar
          :calendar [:crypto :m]
          :asset "BTCUSDT"
          :trailing-n 1000
          :import :bybit
          :feed :bybit
          :algo 'algodemo.reversal-and-breakout.algo/rb-algo
          :len 20
          :vlen 20
          :threshold 2}
   :options [{:type :select
              :path :asset
              :name "Asset"
              :spec ["BTCUSDT" "ETHUSDT"]}
             {:type :select
              :path :trailing-n
              :name "trailing-n"
              :spec [100 300 500 1000 2000 3000 5000 10000]}
             {:type :select
              :path :len
              :name "Length"
              :spec [10 15 20 25 30]}
             {:type :select
              :path :vlen
              :name "V-Length"
              :spec [10 15 20 25 30]}
             {:type :select
              :path :threshold
              :name "Threshold"
              :spec [0.1 0.5 1.0 1.5 2.0 2.5 3.0 5.0]}]
   :chart {:viz 'ta.viz.ds.highchart/highstock-render-spec
           :viz-options {:chart {:box :fl}
                         :charts [{:close :line ; :candlestick
                                   :sh {:type :line :color "green"}
                                   :sl {:type :line :color "red"}
                                   :h {:type :line :color "blue"}
                                   :l {:type :line :color "yellow"}}
                                     ;{:volume :column}
                                  ]}}})

(def eodhd-eod
  {:id :eodhd-eod
   :algo {:type :trailing-bar
          :algo 'algodemo.sma-crossover.algo/sma-crossover-algo
          :calendar [:us :d]
          :asset ""
          :import :eodhd
          :trailing-n 400
          :sma-length-st 20
          :sma-length-lt 200}
   :options [{:type :asset-picker ; custom DYNAMIC UI!!!
              :path :asset
              :name "Asset"}
             {:type :select
              :path :trailing-n
              :name "trailing-n"
              :spec [200 400 600 800 1000 2000 3000 5000 10000]}
             {:type :select
              :path :sma-length-st
              :name "sma-st"
              :spec [10 20 50 100]}
             {:type :select
              :path :sma-length-lt
              :name "sma-lt"
              :spec [100 200 500 1000]}]
   :chart {:viz 'ta.viz.ds.highchart/highstock-render-spec
           :viz-options {:chart {:box :fl}
                         :charts [{:close :candlestick ; :ohlc ; :line 
                                   :sma-lt :line
                                   :sma-st :line}
                                  {:volume :column}]}}})

