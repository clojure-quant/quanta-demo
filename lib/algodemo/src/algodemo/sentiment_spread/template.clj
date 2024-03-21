(ns algodemo.sentiment-spread.template
  (:require
   [algodemo.sentiment-spread.vega]))


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