(ns algodemo.reversal-and-breakout.template)

(def reversal-and-breakout
  {:id :reversal-and-breakout
   :algo {:type :trailing-bar
          :calendar [:crypto :d]
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
                         :charts [{:bar :candlestick
                                   :signal {:type :flags}
                                   :hstore {:type :line :color "green"}
                                   :lstore {:type :line :color "red"}}
                                  {:volume :column}]}}
   :table {:viz 'ta.viz.ds.rtable/rtable-render-spec
           :viz-options {:columns [{:path :date}
                                   {:path :close}
                                   {:path :signal}]}}
   :metrics {:viz 'ta.viz.ds.metrics/metrics-render-spec
             :viz-options {}}})
