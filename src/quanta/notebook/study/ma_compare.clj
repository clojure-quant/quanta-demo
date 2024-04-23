(ns quanta.notebook.study.ma-compare
  (:require
   [fastmath.signal :as s]
   [ta.indicator :as indicator]
   [tablecloth.api :as tc]
   [ta.viz.ds.vega :refer [vega-render-spec]]
   ))

;; create a rectangular signal that is either 1 or 3.

(def wave-rect (s/oscillator :square 0.01 1.0 0.0))

(def signal
  (->> (map wave-rect (range 1000))
       (map inc)
       (map inc)))

;; create a dataset that contains all values

(def ds 
  (tc/dataset {:index (range 1000)
               :close signal
               :sma (indicator/sma {:n 10} signal)
               :ema (indicator/ema 10 signal)}))

ds

(def vega-spec 
  {;:width "1000"
   :box :sm
     ;:width "500" ;"100%"
   :height "200" ;"100%"
   :description "moving average comparison"
   :mark "line"
   :encoding  {;:x "ordinal" ;{:field "index" :type "quantitative"}
               :x {:field :index :type "ordinal"}
               :y {:field :close :type "quantitative"}
               }})


(vega-render-spec {:cols [:close :sma :ema]
                   :spec vega-spec} ds)


