(ns quanta.notebook.dev.lib.javelin
  (:require [javelin.core-clj :refer [cell cell=]])  
  )



  counter (cell= (inc-counter c))

  count-a (atom 0)
  inc-counter (fn [cell]
                (swap! count-a inc))