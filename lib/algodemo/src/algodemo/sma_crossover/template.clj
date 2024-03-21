(ns algodemo.sma-crossover.template)


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
                         :charts [{:ohlc :candlestick ; :ohlc ; :line 
                                   :close :line
                                   :sma-lt :line
                                   :sma-st :line
                                   ;:signal :flags
                                   :signal {:type :flags 
                                            :fillColor "blue"
                                            :width 10
                                            :height 10
                                            :v2style {;:long "square"
                                                      :long "url(/r/arrow-up.svg)"
                                                      true "flags"
                                                      ;:short "circle"
                                                      :short "url(/r/arrow-down.svg)"
                                                       }}}
                                  {:volume :column}]}}
   :table {:viz 'ta.viz.ds.rtable/rtable-render-spec
           :viz-options {:class "table-head-fixed padding-sm table-red table-striped table-hover"
                         :style {:width "50vw"
                                 :height "40vh"
                                 :border "3px solid green"}
                         :columns [{:path :date}
                                   {:path :close}
                                   {:path :sma-st}
                                   {:path :sma-lt}
                                   {:path :position}
                                   {:path :signal}]}}
   :metrics {:viz 'ta.viz.ds.metrics/metrics-render-spec
             :viz-options {}}})



(def eodhd-eod
  {:id :sma-eodhd-eod
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
