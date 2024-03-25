(ns algodemo.multi-ma.algo
  (:require
    [tablecloth.api :as tc]
    [ta.indicator :refer [sma wma ema mma]]))


(defn calc-algo-minute [_env {:keys [ma-type trailing-n] :as opts} bar-ds]
  (let [col (:close bar-ds)
        ma (case ma-type
             :SMA (sma {:n trailing-n} col)
             :WMA (wma trailing-n col)
             :EMA (ema trailing-n col)
             :MMA (mma trailing-n col)
             nil)]
    (if ma
      (tc/add-column bar-ds :ma ma)
      bar-ds)))

(defn calc-algo-day [_env {:keys [ma-type trailing-n] :as opts} bar-ds]
  (let [col (:close bar-ds)
        ma (case ma-type
             :SMA (sma {:n trailing-n} col)
             :WMA (wma trailing-n col)
             :EMA (ema trailing-n col)
             :MMA (mma trailing-n col)
             nil)]
    (if ma
      (tc/add-column bar-ds :ma-day ma)
      bar-ds)))


