(ns demo.goldly.page.test.test
  (:require
   [reagent.core :as r]
   [ta.viz.view.tsymbol :refer [symbol-picker]]
   ;[joseph.upload :refer [upload-file-ui]]
   ))



(defonce symbol-atom (r/atom {:symbol ""}))

(defn test-page [{:keys [_route-params _query-params _handler] :as _route}]
 [:div.bg-blue-300.m-5
 [:p.text-blue.text-xl.bg-yellow-300 "ui component tests"]
   [:div.w-64
    [symbol-picker symbol-atom [:symbol]]
    ;[upload-file-ui]
    ]])