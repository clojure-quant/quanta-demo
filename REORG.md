





environment
- live: one environment for all algos.
- backtest: one environment for each backtest
=> get-env :live :backtest


 :live {:start (ta.algo.env/create-env-javelin (clip/ref :bardb-dynamic
                                                          ;:bardb-dynamic-compressing
                                                          ))}



studio
- get-environment
  - time
  - javelin cells
  - calendars

- templates
- 