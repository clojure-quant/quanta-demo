(ns quanta.notebook.study.ma-compare
  (:require
   [fastmath.signal :as s]
   [ta.indicator :as indicator]
   [ta.indicator.fastmath :refer [lowpass]]
   [tablecloth.api :as tc]
   [ta.viz.ds.vega :refer [vega-render-spec ds-stacked]]
   ))

;; create a rectangular signal that is either 1 or 3.

(def size 300)
(def signal-period 200)

(def wave-rect (s/oscillator :square (/ 1.0 signal-period) 1.0 0.4))

(def signal
  (->> (map wave-rect (range size))
       (map inc)
       (map inc)))

;; create a dataset that contains all values

(def ds 
  (tc/dataset {:index (range size)
               :close signal
               :sma (indicator/sma {:n 10} signal)
               :ema (indicator/ema 10 signal)
               :lowpass (lowpass {:rate 8000.0 :cutoff 2000.0} signal)
               }))

ds

(ds-stacked ds :index [:close :sma :ema :lowpass])


(def vega-spec 
  {:box :sm
   :height "200" ;"100%"
   :width "700"
   :description "moving average comparison"
   :mark "line"
   :encoding  {:x {:field :x :type "ordinal"}
               :y {:field :y :type "quantitative"}
               :color {:field :name, :type "nominal"}
               }})


(vega-render-spec {:cols [:x :y :name]
                   :spec vega-spec}
                  (ds-stacked ds :index [:close :sma :ema :lowpass]))


