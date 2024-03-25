(ns demo.goldly.page.test.color)

(defn box [tailwind-base level]
  [:div {:class (str "bg-" tailwind-base "-" (* 100 level)) 
         :style {:width 20 :height 20}}])

(defn color-row [tailwind-base]
  (into [:div.flex.flex-row
          [:p.p-2.w-32 tailwind-base] 
         ] 
        (map #(box tailwind-base (inc %)) (range 10))))

; https://tailwindcss.com/docs/customizing-colors

(defn color-page [& _route]
  (into [:div.m-5.p-5] 
        (map color-row ["blue" "red" "green" 
                        "gray" "yellow" "indigo"
                        "purple" "pink"
                        ; below does not work. 
                        ; possibly new in tailwind v3
                        "slate"  "zinc" "neutral"
                        "stone" "orange" "amber"
                         "lime"  "emerald"
                        "teal" "cyan" "sky" 
                        "violet"  "fuchsia" 
                        "rose"
                        ])))


