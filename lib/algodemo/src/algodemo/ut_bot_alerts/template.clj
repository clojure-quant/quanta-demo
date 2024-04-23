(ns algodemo.ut-bot-alerts.template)

(def template
  {:id :ut-bot-alerts
   :algo {:algo 'algodemo.ut-bot-alerts.algo/calc-algo
          :type :trailing-bar
          :calendar [:crypto :d]
          :asset "BTCUSDT"
          :trailing-n 100
          :import :bybit
          :feed :bybit
          :mul 1
          :n 10}
   :options [{:type :select
              :path :asset
              :name "Asset"
              :spec ["BTCUSDT" "BTCUSDT.P" "BTCUSD"]}
             {:type :select
              :path :trailing-n
              :name "trailing-n"
              :spec [100 300 500 1000 2000 3000 5000 10000]}
             {:type :select
              :path :mul
              :name "ATR Multiplier"
              :spec (range 0.5 20 0.5)}
             {:type :select
              :path :n
              :name "ATR Period"
              :spec (range 1 51)}
             {:type :select
              :path :calendar
              :name "Cal"
              :spec [[:crypto :d] [:crypto :h] [:crypto :m]]}]
   :chart {:viz 'ta.viz.ds.highchart/highstock-render-spec
           :viz-options {:chart {:box :fl}
                         :charts [{:bar :candlestick
                                   :trailing-stop {:type :line :color "red-5"}
                                   :signal {:type :flags
                                            :long "url(/r/arrow-up.svg)"
                                            true "flags"
                                            :short "url(/r/arrow-down.svg)"}}
                                  {:atr :line}
                                  {:volume {:type :column :color "red-7"}}]}}
   :table {:viz 'ta.viz.ds.rtable/rtable-render-spec
           :viz-options {:columns [{:path :date}
                                   {:path :close}
                                   {:path :atr}
                                   {:path :n-loss}
                                   {:path :trailing-stop}
                                   {:path :above}
                                   {:path :below}
                                   {:path :buy}
                                   {:path :sell}
                                   {:path :signal}]}}
   :metrics {:viz 'ta.viz.ds.metrics/metrics-render-spec
             :viz-options {}}})
