(ns algodemo.reversal-and-breakout.algo
  (:require
    [tablecloth.api :as tc]
    [ta.indicator :refer [sma wma]]
    [ta.indicator.signal :refer [cross-up cross-down]]
    [ta.indicator.rolling :refer [trailing-max trailing-min]]
    [tech.v3.dataset :as ds :refer [replace-missing]]
    [tech.v3.dataset.rolling :as r]
    [tech.v3.datatype.rolling :as rolling]
    [tech.v3.datatype.functional :as dfn]
    [tech.v3.datatype :as dtype]
    ))

;; ALGO: https://www.tradingview.com/script/fpWwOup4-Reversal-and-Breakout-Signals-AlgoAlpha/

(defn store-prev [vec]
  "takes the prev value of the given column or of the new created one.

  because only values of existing columns can be accessed and no state is available during
  processing, two steps are needed to get the previous value.

  first: the column is created only with values where the value changes.
         rows where the old value is needed, we kept empty.
  second: replacing the missing values by using :down strategy (taking previous not missing value)"
  (-> (r/rolling
        (tc/dataset {:in vec})
        {:window-type :fixed
         :window-size 2
         :relative-window-position :left}
        {:out {:column-name [:in]
                 :reducer (fn [[prev cur]]
                            (if (= cur prev) prev))}})
      (replace-missing :down)
      (:out)))

(defn allow-one-signal-every-n-bars [n vec]
  (let [ds (tc/dataset {:in vec})]
    (:out (r/rolling ds
                     {:window-type :fixed
                      :window-size n
                      :relative-window-position :left}
                     {:out {:column-name [:in]
                            :reducer (fn [window]
                                       (if (some true? window)
                                         true
                                         false))}}))))

(defn no-prior-signal-every-n-bars [n vec]
  (let [ds (tc/dataset {:in vec})]
    (:out (r/rolling ds
             {:window-type :fixed
              :window-size n
              :relative-window-position :left}
             {:out {:column-name [:in]
                    :reducer (fn [bool-w]
                               ; aggregate window results and skip the current bool
                               (if (some true? (drop-last bool-w))
                                 false
                                 true))}}))))

(defn bullish-at-level [below above level]
  "calc for every bar if the bullish condition is true: above > level > below"
  (dfn/and
    (dfn/< below level)
    (dfn/> above level)))

(defn bearish-at-level [above below level]
  "calc for every bar if the bearish condition is true: above > level > below"
  (dfn/and
    (dfn/> above level)
    (dfn/< below level)))

(defn- calc-rb-signal [bullish-breakout? bearish-breakout? lcross-over? hcross-under?]
  (cond
    (and bearish-breakout? lcross-over?) :hold
    (and bullish-breakout? hcross-under?) :hold
    bullish-breakout? :long
    bearish-breakout? :short
    :else :hold))

(defn rb-algo [_env {:keys [len vlen threshold] :as opts} bar-ds]
  (let [{:keys [open high low close volume]} bar-ds
        sh (wma len high)
        sl (wma len low)
        h (trailing-max len sh)
        l (trailing-min len sl)

        storeh (store-prev h)
        storel (store-prev l)

        ;; volume cur
        sma-vol (sma {:n vlen} volume)
        sma-r (dfn// volume sma-vol)
        strongvol (dfn/> sma-r threshold)

        ;; TODO: create cross len param for breakout and rejection...
        ;; cross inside window
        breakout  (->> (bullish-at-level open close storeh)
                       (allow-one-signal-every-n-bars 5))

        breakdown (->> (bearish-at-level open close storel)
                       (allow-one-signal-every-n-bars 5))

        strong-breakout (dfn/and breakout strongvol)
        strong-breakdown (dfn/and breakdown strongvol)

        ; breakout signal
        no-prior-bullish-breakout-signals? (no-prior-signal-every-n-bars 4 strong-breakout)
        no-prior-bearish-breakout-signals? (no-prior-signal-every-n-bars 4 strong-breakdown)

        bullish-breakout? (dfn/and strong-breakout no-prior-bullish-breakout-signals?)
        bearish-breakout? (dfn/and strong-breakdown no-prior-bearish-breakout-signals?)

        ; rejection signal
        storel-cross (bullish-at-level low close storel)
        storeh-cross (bearish-at-level high close storeh)

        no-prior-bullish-rejections? (no-prior-signal-every-n-bars 4 storel-cross)
        no-prior-bearish-rejections? (no-prior-signal-every-n-bars 4 storeh-cross)

        bullish-rejection? (dfn/and storel-cross no-prior-bullish-rejections?)
        bearish-rejection? (dfn/and storel-cross no-prior-bearish-rejections?)

        ; signal
        lcross-over? (cross-up low storel)
        hcross-under? (cross-down high storeh)

        signal (into [] (map calc-rb-signal bullish-breakout?
                             bearish-breakout?
                             lcross-over?
                             hcross-under?))]
    (tc/add-columns bar-ds {
                            :sh sh
                            :sl sl
                            :h h
                            :l l
                            :hstore storeh
                            :lstore storel
                            :breakout breakout
                            :breakdown breakdown
                            :sma-vol sma-vol
                            :sma-r sma-r
                            :strongvol strongvol
                            :strong-breakout strong-breakout
                            :strong-breakdown strong-breakdown
                            :no-prior-bullish-breakout-signals? no-prior-bullish-breakout-signals?
                            :no-prior-bearish-breakout-signals? no-prior-bearish-breakout-signals?
                            :bullish-breakout? bullish-breakout?
                            :bearish-breakout? bearish-breakout?
                            :storel-cross storel-cross
                            :storeh-cross storeh-cross
                            :no-prior-bullish-rejections? no-prior-bullish-rejections?
                            :bullish-rejection? bullish-rejection?
                            :no-prior-bearish-rejections? no-prior-bearish-rejections?
                            :bearish-rejection? bearish-rejection?
                            :lcross-over? lcross-over?
                            :hcross-under? hcross-under?
                            :signal signal
                            })
    ))


(comment
  (def ds
    (tc/dataset [{:open 100 :high 120 :low 90 :close 100 :volume 10023}
                 {:open 100 :high 120 :low 90 :close 101 :volume 10050}
                 {:open 101 :high 140 :low 90 :close 130 :volume 11000}
                 {:open 130 :high 140 :low 100 :close 135 :volume 12000}
                 {:open 135 :high 140 :low 110 :close 138 :volume 33000}
                 {:open 138 :high 160 :low 120 :close 150 :volume 55000}
                 {:open 119 :high 160 :low 100 :close 158 :volume 26000}
                 {:open 158 :high 160 :low 120 :close 130 :volume 34000}
                 {:open 130 :high 130 :low 90 :close 120 :volume 13000}
                 {:open 120 :high 140 :low 90 :close 130 :volume 14000}
                 {:open 130 :high 150 :low 90 :close 125 :volume 15000}
                 {:open 125 :high 130 :low 90 :close 120 :volume 12000}
                 {:open 120 :high 120 :low 90 :close 110 :volume 11000}
                 {:open 101 :high 110 :low 90 :close 100 :volume  9000}
                 {:open 100 :high 120 :low 90 :close 110 :volume 11000}]))


  ;; algo
  (rb-algo nil {:len 5 :vlen 5 :threshold 2} ds)



  ds
  (:close ds)
  (wma 5 :close ds)

  (store-prev (:close ds))

  (let [sh (wma 5 (:high ds))
        ds (tc/dataset {:wma sh})]
    (max-ds ds 5 :wma))

  (let [sh (wma 5 (:high ds))
        ds (tc/dataset {:max (trailing-max 5 sh)})]
    ds)

  (let [sl (wma 5 :low ds)]
    (min-ds sl 5 :wma))

  (calc-weight 8 0)

  (rolling/fixed-rolling-window (range 20) 5 dfn/reduce-max {:relative-window-position :left})
  ;(rolling/fixed-rolling-window (:wma sh) 5 dfn/reduce-max {:relative-window-position :left}) ; rounds to one digit, other than rolling/max

  (tc/add-column ds :test 0.0)


  ;; test cross
  (bullish-cross-over-time-ds (tc/dataset [{:open 110 :close 120 :hstore 100}
                                           {:open 120 :close 115 :hstore 100}
                                           {:open 115 :close 105 :hstore 100}
                                           {:open 105 :close 95 :hstore 100}
                                           {:open 95 :close 105 :hstore 100}
                                           ])
                              2 :hstore)

  (bearish-cross-over-time-ds (tc/dataset [{:open 110 :close 120 :hstore 100}
                                           {:open 120 :close 115 :hstore 100}
                                           {:open 115 :close 105 :hstore 100}
                                           {:open 105 :close 95 :hstore 100}
                                           {:open 95 :close 105 :hstore 100}
                                           {:open 105 :close 115 :hstore 100}
                                           {:open 115 :close 125 :hstore 100}
                                           ])
                              3 :hstore)

  (dfn/> [1 2 3] [2 2 2])

  (require '[ta.series.indicator :refer [ago]])
  (ago 1 (:high ds))

  ;(r/rolling ds
  ;           {:window-type :fixed
  ;            :window-size 5
  ;            :relative-window-position :left}
  ;           {:idx })

  (r/rolling (ds/->dataset (for [x [1 2 3 4 5 6 7 8 9 10]] {:high x}))
             {:window-type :fixed
              :window-size 3
              :relative-window-position :left}
             {:a {:column-name [:high]
                  :reducer (fn [rdr]
                             [(rdr 0) (rdr 1) (rdr 2)])}
              :b {:column-name [:a]
                  :reducer (fn [rdr]
                             [(rdr 0) (rdr 1) (rdr 2)])}})

  (r/rolling (ds/select-columns ds [:high])
             {:window-type :fixed
              :window-size 4
              :relative-window-position :left}
             {:a {:column-name [:high]
                  :reducer (fn [rdr]
                             [(rdr 0) (rdr -1) (rdr -2) (rdr -3)])}})



  (partition 3 1 (:high ds))

  ;; emap

  (ds/->dataset {:a (range 10)
                 :b (dtype/emap (fn [^long i]
                                  i)
                                :int64
                                (:high ds))})

  ;; reader

  (require '[tech.v3.datatype :as dtype])
  (dtype/make-reader :float32 5 idx)

  (let [data (dtype/->reader (:high ds))]
    (dtype/make-reader :float64  (.lsize data)
                       (.readDouble data (max 0 (dec idx)))))

  (ds/->dataset {:a (range 10)
                 :b (dtype/make-reader :int64 10
                                       (let [i idx] i))
                 })

  (let [ds-res (rb-algo nil {:len 5 :vlen 5 :threshold 2} ds)]
    (cross-up (:low ds-res) (:lstore ds-res)))


  )


(comment
  (-> (ds/->dataset (for [h [100 101 102 103 103 103 107 106 105 105 104]] {:h h}))
      (r/rolling
        {:window-type :fixed
         :window-size 2
         :relative-window-position :left}
        {:hstore {:column-name [:h]
                  :reducer (fn [[prev cur]]
                             (let [cur-hstore (if (= cur prev) prev)]
                               ))}})
      (replace-missing :down))


  (c/quick-bench)
  )