(ns algodemo.reversal-and-breakout.algo
  (:require
    [tablecloth.api :as tc]
    [ta.indicator :refer [sma wma]]
    [ta.indicator.signal :refer [cross-up cross-down]]
    [tech.v3.dataset :as ds :refer [row-map replace-missing]]
    [tech.v3.dataset.rolling :as r]
    [tech.v3.datatype.rolling :as rolling]
    [tech.v3.datatype.functional :as dfn]
    [tech.v3.datatype :as dtype]
    ))

;; ALGO: https://www.tradingview.com/script/fpWwOup4-Reversal-and-Breakout-Signals-AlgoAlpha/

(defn max-ds [ds len of]
  (r/rolling ds
             {:window-type :fixed
              :window-size len
              :relative-window-position :left}
             {:max (r/max of)}))

(defn min-ds [ds len of]
  (r/rolling ds
             {:window-type :fixed
              :window-size len
              :relative-window-position :left}
             {:min (r/min of)}))

(defn store-ds [ds of]
  "creates a column which takes the value of an other column or keeps the previous taken value.

  because only values of existing columns can be accessed and no state is available during
  processing, two steps are needed to get the previous value.

  first: the column is created only with values where the value changes.
         rows where the old value is needed, we kept empty.
  second: replacing the missing values by using :down strategy (taking previous not missing value)"
  (-> ds
      (r/rolling
        {:window-type :fixed
         :window-size 2
         :relative-window-position :left}
        {:store {:column-name [of]
                 :reducer (fn [[prev cur]]
                            (if (= cur prev) prev))}})
      (replace-missing :down)))

(defn bullish-cross [below-w above-w level-w]
  "calc level cross inside a bar. eq: open - close, low - close"
  (dfn/and
    (dfn/< below-w level-w)
    (dfn/> above-w level-w)))

(defn bearish-cross [above-w below-w level-w]
  "calc level cross inside a bar. eq: open - close, low - close"
  (dfn/and
    (dfn/> above-w level-w)
    (dfn/< below-w level-w)))

(defn bullish-cross-over-time-ds [ds len below-of above-of level-of]
  "bullish cross of a level by col1 and col2 values"
  (r/rolling ds
             {:window-type :fixed
              :window-size len
              :relative-window-position :left}
             {:breakout {:column-name [below-of above-of level-of]
                         :reducer (fn [below-w above-w level-w]
                                    ; aggregate window results
                                    (if (some true? (bullish-cross below-w above-w level-w))
                                      true
                                      false))}}))

(defn bearish-cross-over-time-ds [ds len above-of below-of level-of]
  "bearish cross of a level by col1 and col2 values"
  (r/rolling ds
             {:window-type :fixed
              :window-size len
              :relative-window-position :left}
             {:breakdown {:column-name [above-of below-of level-of]
                          :reducer (fn [above-w below-w level-w]
                                     ; aggregate window results
                                     (if (some true? (bearish-cross above-w below-w level-w))
                                       true
                                       false))}}))

(defn no-prior-signal-over-time? [ds trailing-n of]
  (r/rolling ds
             {:window-type :fixed
              :window-size trailing-n
              :relative-window-position :left}
             {:prior-signals? {:column-name [of]
                               :reducer (fn [bool-w]
                                          ; aggregate window results and skip the current bool
                                          (if (some true? (drop-last bool-w))
                                            false
                                            true))}}))

(defn- calc-rb-signal [bullish-breakout? bearish-breakout? lcross-over? hcross-under?]
  (cond
    (and bearish-breakout? lcross-over?) :hold
    (and bullish-breakout? hcross-under?) :hold
    bullish-breakout? :long
    bearish-breakout? :short
    :else :hold))

(defn rb-algo [_env {:keys [len vlen threshold] :as opts} bar-ds]
  (let [
        sh (ds/add-column bar-ds (wma len (:high bar-ds)))
        sl (ds/add-column bar-ds (wma len (:low bar-ds)))
        h (max-ds sh len :wma)
        l (min-ds sl len :wma)

        storeh (store-ds h :max)
        storel (store-ds l :min)

        ; volume cur
        sma-vol (sma {:n vlen} (:volume bar-ds))
        sma-r (dfn// (:volume bar-ds) sma-vol)
        strongvol (dfn/> sma-r threshold)

        ; TODO: create cross len param for breakout and rejection...
        ; cross inside window
        breakout (bullish-cross-over-time-ds storeh 5 :open :close :store)
        breakdown (bearish-cross-over-time-ds storel 5 :open :close :store)

        strong-breakout (dfn/and (:breakout breakout) strongvol)
        strong-breakdown (dfn/and (:breakdown breakdown) strongvol)

        ; breakout signal
        no-prior-bullish-breakout-signals? (no-prior-signal-over-time? (tc/dataset {:signal strong-breakout})
                                                                       2 :signal)
        no-prior-bearish-breakout-signals? (no-prior-signal-over-time? (tc/dataset {:signal strong-breakdown})
                                                                       2 :signal)

        bullish-breakout? (dfn/and strong-breakout
                                   (:prior-signals? no-prior-bullish-breakout-signals?))

        bearish-breakout? (dfn/and strong-breakdown
                                   (:prior-signals? no-prior-bearish-breakout-signals?))

        ; rejection signal
        storel-cross (bullish-cross (:low bar-ds) (:close bar-ds) (:store storel))
        storeh-cross (bearish-cross (:high bar-ds) (:close bar-ds) (:store storeh))

        no-prior-bullish-rejections? (no-prior-signal-over-time? (tc/dataset {:signal storel-cross})
                                                                 2 :signal)

        no-prior-bearish-rejections? (no-prior-signal-over-time? (tc/dataset {:signal storeh-cross})
                                                                 2 :signal)

        bullish-rejection? (dfn/and storel-cross
                                    (:prior-signals? no-prior-bullish-rejections?))
        bearish-rejection? (dfn/and storel-cross
                                    (:prior-signals? no-prior-bearish-rejections?))

        ; signal
        lcross-over? (cross-up (:low bar-ds) (:store storel))
        hcross-under? (cross-down (:high bar-ds) (:store storeh))

        signal (into [] (map calc-rb-signal bullish-breakout?
                             bearish-breakout?
                             lcross-over?
                             hcross-under?))]
    (tc/add-columns bar-ds {
                            :sh (:wma sh)
                            :sl (:wma sl)
                            :h (:max h)
                            :l (:min l)
                            :hstore (:store storeh)
                            :lstore (:store storel)
                            :breakout (:breakout breakout)
                            :breakdown (:breakdown breakdown)
                            :sma-vol sma-vol
                            :sma-r sma-r
                            :strongvol strongvol
                            :strong-breakout strong-breakout
                            :strong-breakdown strong-breakdown
                            :no-prior-bullish-breakout-signals? (:prior-signals? no-prior-bullish-breakout-signals?)
                            :no-prior-bearish-breakout-signals? (:prior-signals? no-prior-bearish-breakout-signals?)
                            :bullish-breakout? bullish-breakout?
                            :bearish-breakout? bearish-breakout?
                            :storel-cross storel-cross
                            :storeh-cross storeh-cross
                            :no-prior-bullish-rejections? (:prior-signals? no-prior-bullish-rejections?)
                            :bullish-rejection? bullish-rejection?
                            :no-prior-bearish-rejections? (:prior-signals? no-prior-bearish-rejections?)
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

  (let [sh (wma 5 :high ds)]
    (max-ds sh 5 :wma))

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