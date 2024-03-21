(ns algodemo.asset-compare.template
  (:require
    [algodemo.asset-compare.viz]))


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