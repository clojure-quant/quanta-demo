(ns algodemo.linear-regression-candles.algo
  (:require
    [tablecloth.api :as tc]
    [ta.indicator :refer [sma wma ema mma]]
    [ta.indicator.rolling :refer [trailing-linear-regression]]
    [ta.indicator.signal :refer [cross-up cross-down]]))

(defn add-linreg-candles [{:keys [open high low close] :as bar-ds} n]
  (-> (tc/add-columns bar-ds {:open-linreg  (trailing-linear-regression n open)
                              :high-linreg  (trailing-linear-regression n high)
                              :low-linreg   (trailing-linear-regression n low)
                              :close-linreg (trailing-linear-regression n close)})
      ; remove first row with nil values
      (tc/drop-rows 0)))

(defn add-ma [ds t n]
  (let [{:keys [close-linreg]} ds
        linreg-ma (case t
                    :SMA (sma {:n n} close-linreg)
                    :WMA (wma n close-linreg)
                    :EMA (ema n close-linreg)
                    :MMA (mma n close-linreg)
                    nil)]
    (tc/add-column ds :linreg-ma linreg-ma)))

(defn calc-signal-row [cross-up cross-down]
  (cond
    cross-up :long
    cross-down :short
    :else :hold))

(defn add-signal [ds]
  (let [cross-up (cross-up (:close-linreg ds) (:linreg-ma ds))
        cross-down (cross-down (:close-linreg ds) (:linreg-ma ds))
        signal (into [] (map calc-signal-row cross-up cross-down))]
    (tc/add-column ds :signal signal)))

(defn calc-algo [_env {:keys [ma-type ma-len linreg-len]} bar-ds]
  (-> bar-ds
      (add-linreg-candles linreg-len)
      (add-ma ma-type ma-len)
      (add-signal)))