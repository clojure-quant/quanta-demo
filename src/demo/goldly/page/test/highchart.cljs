(ns demo.goldly.page.test.highchart
  (:require
   [reagent.core :as r]
   [ui.highcharts :refer [highchart highstock add-annotations add-boost add-debugger]]
   ))


; this is our helper function to assemble a highchart object. it contains
; default values that we will use for multiple charts
(defn make-chart-config [data]
  {:chart {:type "line"
           :animation false
           ; zoom/pan
           :zooming {; https://api.highcharts.com/highcharts/chart.zooming.type
              :key "alt" ; Should be set different than chart.panKey.
              :type "x"}
           :panning {:enabled true
                     :type "x" ; "xy"
                     }
           :panKey "ctrl" ; "alt"" "shift"  "meta"
           }
   :title {:text (:title data)}
   :subtitle {:text (:subtitle data)}
   :backgroundColor "blue"
   :yAxis {:min 0
           :title {:text ""
                   :align "high"}}
               ;:labels {:overflow "justify"}

   :xAxis {:categories (:labels data)
           :plotBands [{:color "rgba(255,75,66,0.07)"
                        :from 4
                        :to 5
                        :label {:text "forecast"}
                        :zIndex 1000}]}

   :tooltip {:valueSuffix " %"}
   :plotOptions {:series
                 {:animation 0
                  :label
                  {;:pointStart 2010
                   :connectorAllowed false}}}
   :legend {;:x -40
            ;:y 100
            ;:floating true
            ;:borderWidth 1
            ;:shadow true
            :layout "vertical"
            :align "right"
            :verticalAlign "top"}
   :credits {:enabled false}
   :series (:series data)
   })

(def highchart-spec
  (make-chart-config
   {:title "Economic Activity"
    :subtitle "made with Love"
    :labels ["Jan" "Feb" "Mar" "Apr" "May" "Jun" "Jul" "Aug"]
    :series
    [{:name "Installation" :data [439 523 577 698 931 1131 1333 1175]}
     {:name "Manufacturing" :data [249 244 292 291 390 302 381 404]}
     {:name "Sales & Distribution" :data [117 172 165 191 285 247 321 393]}
     {:name "range" :data [[100 320] [120 350] [160 370] [180 395] [280 490] [240 560] [300 430] [390 600]] :type "arearange"}
     ]}))


(defn highchart-page [{:keys [_route-params _query-params _handler] :as _route}]
  [:div.bg-blue-300.m-5
   [:p.text-blue.text-xl.bg-yellow-300 "highchart test"]
    [highchart {:data highchart-spec :box :md}]
    
    ])

(def highstock-spec 
  (assoc highchart-spec 
         :legend {:enabled false}
         ; highstock specific starting here:
         :rangeSelector {:enabled false}
         ; The navigator is a small series below the main series, displaying a view of the entire data set.
         :navigator {:enabled false}
         
         )
  )

(defn highstock-page [{:keys [_route-params _query-params _handler] :as _route}]
  [:div.bg-blue-300.m-5
   [:p.text-blue.text-xl.bg-yellow-300 "highstock test"]
   [highstock {:data highstock-spec :box :md}]])