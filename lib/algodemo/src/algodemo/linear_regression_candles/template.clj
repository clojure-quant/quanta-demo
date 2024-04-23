(ns algodemo.linear-regression-candles.template)

(def template
  {:id :linear-regression-candles
   :algo {:algo 'algodemo.linear-regression-candles.algo/calc-algo
          :type :trailing-bar
          :calendar [:crypto :d]
          :asset "BTCUSDT"
          :trailing-n 100
          :import :bybit
          :feed :bybit
          :ma-type :SMA
          :ma-len 11
          :linreg-len 11}
   :options [{:type :select
              :path :assets
              :name "Asset"
              :spec ["BTCUSDT" "BTCUSDT.P" "BTCUSD"]}
             {:type :select
              :path :trailing-n
              :name "trailing-n"
              :spec [100 300 500 1000 2000 3000 5000 10000]}
             {:type :select
              :path :ma-type
              :name "MA Type"
              :spec [:OFF :SMA :WMA :EMA :MMA]}
             {:type :select
              :path :ma-len
              :name "MA Len"
              :spec (range 1 51)}
             {:type :select
              :path :linreg-len
              :name "Linreg Len"
              :spec (range 1 51)}
             {:type :select
              :path :calendar
              :name "Cal"
              :spec [[:crypto :d] [:crypto :h] [:crypto :m]]}]
   :chart {:viz 'ta.viz.ds.highchart/highstock-render-spec
           :viz-options {:chart {:box :fl}
                         :charts [{:close-linreg :step
                                   :linreg-ma {:type :line :color "red-6"}}
                                  {:volume {:type :column :color "red-7"}}]}}
   :table {:viz 'ta.viz.ds.rtable/rtable-render-spec
           :viz-options {:columns [{:path :date}
                                   {:path :close}
                                   {:path :close-linreg}
                                   {:path :linreg-ma}
                                   {:path :signal}]}}
   :metrics {:viz 'ta.viz.ds.metrics/metrics-render-spec
             :viz-options {}}})
