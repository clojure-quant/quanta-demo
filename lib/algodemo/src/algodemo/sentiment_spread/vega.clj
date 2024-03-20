(ns algodemo.sentiment-spread.vega)

(def w 1600)

(def spec
  {;:width "1000"
   :box :fl
   :width w ;"100%"
   :height "400" ;"100%"
   :description "Market-Sentiment"
   :params [{:name "currentyear"
             :value 2024
             :bind {:name "Year"
                    :input "range"
                    :min 2009 :max 2024 :step 1}}]
   :transform [{:calculate "year(datum.date)" :as "year"}
               {:filter "datum.year == currentyear"}]
   :vconcat [{:height 500
              :width w
              :title "Market"
              :layer [{:mark {:type "text"
                              :fontSize 100
                              :x 420
                              :y 600
                              :opacity 0.06},
                       :encoding {:text {:field "year"}}}
                      {:mark "rule"
                       :transform [{:filter "datum.sentiment > 4"}]
                       :encoding {:color {:value "blue"}
                                  :size {:value 5}
                                  :x {:field :date :type "temporal"}}}
                      {:mark "rule"
                       :transform [{:filter "datum.sentiment < -4"}]
                       :encoding {:color {:value "red"}
                                  :size {:value 5}
                                  :x {:field :date :type "temporal"}}}
                      {:mark "line"
                       :encoding {:x {:field "date" :type "temporal"}
                                  :y {:field "market" :type "quantitative" :color "blue"
                                      :scale {:type "linear" :zero false}}}}]}
             {:title "Sentiment"
              :height 100
              :width w
              :mark "bar"
              :encoding {:x {:field "date" :type "temporal"}
                         :y {:field "sentiment" :type "quantitative" :color "blue"}}}]})

(def vega-spec 
  {:cols [:date :sentiment :market]
   :spec spec})
