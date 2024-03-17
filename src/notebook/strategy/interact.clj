(ns notebook.strategy.interact
  (:require
   [ta.interact.template :as template]
   [ta.db.asset.db :as db]
   [juan.asset-pairs :as juan-assets]
   ; vega specs here:
   [notebook.strategy.sentiment-spread.vega]
   [notebook.strategy.asset-compare.viz]
   [juan.notebook.viz]))

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
          :algo 'notebook.strategy.sma-crossover.algo/sma-crossover-algo
          :calendar [:forex :m]
          :asset "ETHUSDT"
          :feed :bybit
          :import :bybit
          :trailing-n 1000
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
          :algo 'notebook.strategy.sentiment-spread.algo/sentiment-spread
          :calendar [:us :d]
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
           :viz-options notebook.strategy.sentiment-spread.vega/vega-spec}})

(def juan-fx
  {:id :juan-fx
   :algo  [{:asset "USD/JPY"}
           :day {:type :trailing-bar
                 :algo   ['juan.algo.intraday/ensure-date-unique
                          'juan.algo.daily/daily]
                 :calendar [:forex :d]
                 :import :kibot
                 :feed :fx
                 ; daily opts
                 :trailing-n 120
                 ; atr-band
                 :atr-n 10
                 :atr-m 0.5
                 ; price pivots
                 :step 0.0001
                 :percentile 70}
           :minute {:calendar [:forex :m]
                    :algo  ['juan.algo.intraday/ensure-date-unique
                            'juan.algo.doji/doji-signal]
                    :type :trailing-bar
                    ;:import :kibot-http ; in live mode dont import
                    :trailing-n 1440 ; 24 hour in minute bars
                     ;  doji
                    :max-open-close-over-low-high 0.3
                    :volume-sma-n 30
            ; volume-pivots (currently not added)
            ;:step 10.0
            ;:percentile 70
                    }
           :signal {:formula [:day :minute]
                    :pivot-max-diff 0.001
                    :algo 'juan.algo.combined/daily-intraday-combined}]
   :options [{:type :select
              :path [0 :asset]
              :name "asset"
              :spec juan-assets/spot-fx-assets}
             {:type :select
              :path [2 :trailing-n]
              :name "daily#"
              :spec [2 5 10 20 30 50 80 100 120 150]}
             {:type :select
              :path [4 :trailing-n]
              :name "i#"
              :spec [720 1440 2880 5000 10000]}
             {:type :select
              :path [2 :atr-n]
              :name "dATRn"
              :spec [3 5 7 10 20 30 40 50]}
             {:type :select
              :path [2 :atr-m]
              :name "dATRm"
              :spec [0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 0.9 1.0 1.1 1.2 1.3]}
             {:type :select
              :path [2 :percentile]
              :name "dPercentile"
              :spec [10 20 30 40 50 60 70 80 90]}
             {:type :select
              :path [2 :step]
              :name "dStep"
              :spec [0.001 0.0001 0.00004]}
             {:type :select
              :path [4 :max-open-close-over-low-high]
              :name "doji-co/lh max"
              :spec [0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 0.9]}
             {:type :select
              :path [4 :calendar 1]
              :name "intraday-interval"
              :spec [:m :h]}]

   :chart {:viz 'ta.viz.ds.highchart/highstock-render-spec
           :viz-options juan.notebook.viz/combined-chart-spec
           :key :signal}})

(def asset-compare
  {:id :asset-compare
   :algo {:type :time
          :algo 'notebook.strategy.asset-compare.algo/asset-compare-algo
          :calendar [:us :d]
          :assets ["GLD" "UUP" "SPY" "QQQ" "IWM" "EEM" "EFA" "IYR" "USO" "TLT"]
          :import :kibot
          :trailing-n 1000}
   :chart {:viz 'ta.viz.ds.vega/vega-render-spec
           :viz-options notebook.strategy.asset-compare.viz/vega-spec}})

(def reversal-and-breakout
  {:id :reversal-and-breakout
   :algo {:type :trailing-bar
          :calendar [:crypto :m]
          :asset "BTCUSDT"
          :trailing-n 1000
          :import :bybit
          :feed :bybit
          :len 20
          :vlen 20
          :threshold 2
          :algo 'notebook.strategy.reversal-and-breakout.algo/rb-algo}
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
(def astro-chart
  {:id :astro-chart
   :algo {:type :time
          :algo 'astro.algo/astro-algo
          :calendar [:crypto :m]}
   :options [{:type :select
              :path :asset
              :name "Asset"
              :spec ["BTCUSDT" "ETHUSDT"]}]
   :chart {:viz 'astro.hiccup/astro-hiccup}})

(def eodhd-eod
  {:id :eodhd-eod
   :algo {:type :trailing-bar
          :algo 'notebook.strategy.sma-crossover.algo/sma-crossover-algo
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

(def gann
  {:id :gann
   :algo {:type :trailing-bar
          :algo 'ta.gann.algo/algo-gann-signal
          :asset "BTCUSDT"
          :import :bybit
          :calendar [:crypto :d]
          :box {:ap 8000.0
                :at 180
                :bp 12000.0
                :bt 225}}
   :options [{:type :string
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
                         :charts  [{:sr-up-0 "line"
                                    :sr-up-1 "line"
                                    :sr-up-2 "line"
                                    :sr-down-0 {:type "line" :color "red"}
                                    :sr-down-1 {:type "line" :color "red"}
                                    :sr-down-2 {:type "line" :color "red"}}
                                   {:cross-up-close "column"
                                    :cross-down-close "column"}
                                   {:qp "column"
                             ;:qt "column"
                                    }
                           ;{:index "column"}
                          ; {:qt-jump-close "column"}
                                   ]}}})
(defn add-templates []
  (doall
   (map template/add [watch-crypto
                      sma-crypto
                      sentiment-spread
                      asset-compare
                      juan-fx
                      reversal-and-breakout
                      astro-chart
                      eodhd-eod
                      gann])))

(comment
  (add-templates)

 ; 
  )