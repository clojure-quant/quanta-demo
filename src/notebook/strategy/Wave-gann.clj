
(Defn wave-trend [bar-ds n1 n2]
(let [ap (hlc3 bar-ds)
        esa (ema n1 ap) 
        diff (- ap esa)
        Abs-diff (abs diff)
        d (ema n1 abs-diff) 
        ci  (/ (- ap esa) (* 0.015 d)
        tci (ema n2 ci)]
      tci))

// Signal Funktion 
var lastSignalBar = 0 
longSignal1 = crossover(close, hma_bottom_band1 and (bar_index - lastSignalBar > signalCooldown 
shortSignal1 = crossunder(close, hma_top_band1 and (bar_index - lastSignalBar > signalCooldown

(defn one-signal-every-bars [n signal]
    (Trailing-window 1-prior (dec n)
        

(defn acceleration-factor [prior wt price]
 (Let [xup? (cross-up wt price)
         xdown? (cross-down wt price)]
  (Cond (not xdown?)
             1
             Xup?
             Nil
             :else
             Prior)))

(Defn mult-factor [level af ]
  (If af 1 level))

(defn wt-af [level wt price]
  (Let [af (acceleration-factor wt price)
     (Mult-factor level af)))


(defn hull-ma-band [{:keys [wma-n m l-up l-down]} price]
 (let [fast (wma wma-n price)
        slow (wma (* 2 wma-n) price)
        diff (-> fast (fun/* 2.0) (fun/- slow))
        combined-n (-> wma-n (* 2) sqrt round)
        Hullma (wma diff combined-n)
        Band-height (fun/* m (atr wma-n))
        a-top (wt-af l-up wt price)
        a-low (wt-af l-down wt price)]
  {:upper (fun/+ hullma (fun/* band-height a-top))
   :lower (fun/-  hullma (fun/* band-height a-low))



(def config {1 {:wma-n 250 :m 20 :l-up 1.5 :l-down 3.0}
             2 {:wma-n 125 :m 10 :l-up 2.0 :l-down 2.0}
             3 {:wma-n 50 :m 5 :l-up 2.5 :l-down 1.5}
             :wt {:n1 10 :n2 40}})

