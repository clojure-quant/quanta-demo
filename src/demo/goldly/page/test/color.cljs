(ns demo.goldly.page.test.color)

(defn box [tailwind-base level]
  [:div {:class (str "bg-" tailwind-base "-" (* 100 level)) 
         :style {:width 20 :height 20}}])

(defn color-row [tailwind-base]
  (into [:div.flex.flex-row] 
        (map #(box tailwind-base (inc %)) (range 10))))

(defn color-page [& _route]
  (into [:div.m-5.p-5] 
        (map color-row ["blue" "red" "green"])))


