(ns notebook.strategy.bollinger-old.optimizer
  (:require
   [taoensso.timbre :refer [info]]
   [tablecloth.api :as tc]
   [ta.algobacktest.backtester :as backtest]
   [demo.study.bollinger :as bs]))

(def default-options {:w :crypto
                      :frequency "D"
                      :symbol "ETHUSD"
                      :sma-length 20
                      :stddev-length 20
                      :mult-up 2.0
                      :mult-down 2.0
                      :forward-size 20})

(defn task-bollinger-study []
  (info "running bollinger strategy with options: " default-options)
  (let [r (backtest/run-study bs/bollinger-study
                              default-options)]
    (bs/print-all r :ds-study)
    (bs/print-all r :ds-events-all)
    (bs/print-all r :ds-events-forward)
    (bs/print-all r :ds-performance)
    (bs/print-backtest-numbers r)

    (backtest/save-study :crypto (:ds-study r) "ETHUSD" "D" "bollinger-upcross")
    (info "study calculation finished.")))

(defn task-bollinger-optimizer []
  (info "running bollinger strategy optimizer")
  (->
   (for [length (range 10 200 10)
      ; mult-up (range 0.5 3.5 0.5)
         ]
     (let [options {:w :crypto
                    :frequency "D"
                    :symbol "ETHUSD"
                    :sma-length length
                    :stddev-length length
                    :mult-up  1.5
                    :mult-down 1.5
                    :forward-size length}
           r (backtest/run-study bs/bollinger-study
                                 options)]
       (:backtest-numbers r)))
   (tc/dataset)
   (bs/print-ds-cols-all [:sma-length
                          :down-count
                          :up-count
                          :goodness])))

(comment

  (task-bollinger-study)

  (task-bollinger-optimizer)

 ; 
  )