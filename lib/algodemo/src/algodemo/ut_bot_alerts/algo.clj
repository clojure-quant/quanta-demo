(ns algodemo.ut-bot-alerts.algo
  (:require
    [tablecloth.api :as tc]
    [ta.indicator :refer [atr ema]]
    [ta.indicator.helper :refer [indicator]]
    [ta.indicator.signal :refer [cross-up cross-down]]
    [tech.v3.datatype.functional :as dfn]))

(defn calc-trailing-stop
  "trailing stop"
  [col n-loss]
  (indicator
    [prev-trailing-stop (volatile! nil)]
    (fn [idx]
      (let [price (get col idx)
            prev-price (if (> idx 0)
                         (get col (dec idx))
                         price)
            offset (get n-loss idx)
            prev-ts (if @prev-trailing-stop @prev-trailing-stop 0.0)
            next-ts (cond
                      ; first value
                      (= idx 0)
                      0.0

                      ; long ts (2 bars)
                      (and (> price prev-ts) (> prev-price prev-ts))
                      (max prev-ts (- price offset))

                      ; short ts (2 bars)
                      (and (< price prev-ts) (< prev-price prev-ts))
                      (min prev-ts (+ price offset))

                      ; long ts
                      (> price prev-ts)
                      (- price offset)

                      ; short ts
                      :else
                      (+ price offset))]
        (vreset! prev-trailing-stop next-ts)
        next-ts))))

(defn calc-signal [buy sell]
  (cond
    buy :long
    sell :short
    :else :hold))

(defn calc-algo [_env {:keys [mul n] :as opts} bar-ds]
  (let [;stop-loss
        atr (atr {:n n} bar-ds)
        n-loss (dfn/* atr mul)
        close (:close bar-ds)
        trailing-stop (into [] (calc-trailing-stop close n-loss)
                            (range 0 (count n-loss)))
        ; signal
        ema (ema 1 close)
        above (cross-up ema trailing-stop)
        below (cross-down ema trailing-stop)
        buy (dfn/and
              (dfn/> close trailing-stop)
              above)
        sell (dfn/and
               (dfn/< close trailing-stop)
               below)]
    (-> (tc/add-columns bar-ds {:atr atr
                            :n-loss n-loss
                            :trailing-stop trailing-stop
                            :ema ema
                            :above above
                            :below below
                            :buy buy
                            :sell sell
                            :signal (map calc-signal buy sell)})
        ; drop 0.0 trailing stop
        (tc/drop-rows 0))))

(comment
  (require '[tick.core :as t])

  (def ds
    (tc/dataset [{:date (t/instant "2019-11-01T00:00:00.000Z") :open 100.0 :high 120.1 :low 90.033 :close 100.0 :volume 10023}
                 {:date (t/instant "2019-11-02T00:00:00.000Z") :open 100.0 :high 120.2 :low 90.044 :close 101.0 :volume 10050}
                 {:date (t/instant "2019-11-03T00:00:00.000Z") :open 101.0 :high 140.3 :low 90.055 :close 130.0 :volume 11000}
                 {:date (t/instant "2019-11-04T00:00:00.000Z") :open 130.0 :high 140.4 :low 100.066 :close 135.0 :volume 12000}
                 {:date (t/instant "2019-11-05T00:00:00.000Z") :open 135.0 :high 140.5 :low 110.077 :close 138.0 :volume 33000}
                 {:date (t/instant "2019-11-06T00:00:00.000Z") :open 138.0 :high 160.6 :low 120.088 :close 150.0 :volume 55000}
                 {:date (t/instant "2019-11-07T00:00:00.000Z") :open 119.0 :high 160.7 :low 100.099 :close 158.0 :volume 26000}
                 {:date (t/instant "2019-11-08T00:00:00.000Z") :open 158.0 :high 160.8 :low 120.088 :close 130.0 :volume 34000}
                 {:date (t/instant "2019-11-09T00:00:00.000Z") :open 130.0 :high 130.9 :low 90.077 :close 120.0 :volume 13000}
                 {:date (t/instant "2019-11-10T00:00:00.000Z") :open 120.0 :high 140.8 :low 90.066 :close 130.0 :volume 14000}
                 {:date (t/instant "2019-11-11T00:00:00.000Z") :open 130.0 :high 150.7 :low 90.055 :close 125.0 :volume 15000}
                 {:date (t/instant "2019-11-12T00:00:00.000Z") :open 125.0 :high 130.6 :low 90.044 :close 120.0 :volume 12000}
                 {:date (t/instant "2019-11-13T00:00:00.000Z") :open 120.0 :high 120.0 :low 90.033 :close 110.0 :volume 11000}
                 {:date (t/instant "2019-11-14T00:00:00.000Z") :open 101.0 :high 110.0 :low 88.022 :close 89.0 :volume  9000}
                 {:date (t/instant "2019-11-15T00:00:00.000Z") :open 100.0 :high 120.0 :low 90.011 :close 110.0 :volume 11000}]))

  (calc-algo nil {:mul 1 :n 10} ds)
  )
