(ns algodemo.multi-ma.template)

(def multi-ma
  {:id :multi-ma
   :algo [{:asset "BTCUSDT"}
          :minute {:type :trailing-bar
                    :calendar [:crypto :m]
                    :trailing-n 1440
                    :import :bybit
                    :feed :bybit
                    :algo 'algodemo.multi-ma.algo/calc-algo-minute
                    :ma-type :SMA}
          ;:day {:type :trailing-bar
          ;       :calendar [:crypto :d]
          ;       :trailing-n 120
          ;       :import :bybit
          ;       :feed :bybit
          ;       :algo 'algodemo.multi-ma.algo/calc-algo-day
          ;       :ma-type :OFF}
          ]
   :options [{:type :select
              :path [0 :asset]
              :name "Asset"
              :spec ["BTCUSDT" "ETHUSDT"]}
             ; minute
             {:type :select
              :path [2 :trailing-n]
              :name "trailing-n (M)"
              :spec [720 1440 2880 5760 10000]}
             {:type :select
              :path [2 :ma-type]
              :name "MA Type (M)"
              :spec [:OFF :SMA :WMA :EMA :MMA]}
             ; day
             ;{:type :select
             ; :path [4 :trailing-n]
             ; :name "trailing-n (D)"
             ; :spec [2 5 10 20 30 50 80 100 120 150]}
             ;{:type :select
             ; :path [4 :ma-type]
             ; :name "MA Type (D)"
             ; :spec [:OFF :SMA :WMA :EMA :MMA]}
             ]
   :chart {:viz 'ta.viz.ds.highchart/highstock-render-spec
           :viz-options {:chart {:box :fl}
                         :charts [{:close :line
                                   :ma {:type :line}
                                   ;:ma-day {:type :line}
                                   }
                                  {:volume :column}]}
           ;:key :minute
           }
   ;:table {:viz 'ta.viz.ds.rtable/rtable-render-spec
   ;        :viz-options {:columns [{:path :date}
   ;                                {:path :close}
   ;                                {:path :signal}]}}
   ;:metrics {:viz 'ta.viz.ds.metrics/metrics-render-spec
   ;          :viz-options {}}
   })
