# quanta-demo


## run demo 

This will run quanta-studio; the ui can be accessed on http://localhost:8080/.

*start with public algos enabled*

```
clj -X:webly:run:public-algos
```

*start with private algos enabled as well*
```
clj -X:webly:run:public-algos:my-algos
```

## Notebook Viewer

To eval all notebooks, run:
```
clj -X:nbeval
```


## VAULT

Datafeeds require api credentials. In `app/vault` there is `goldly/trateg.edn`.
Modify this file to use your credentials. Then set the environment variable *MYVAULT*
so that it points to *app/vault* , example:
`export MYVAULT=/home/awb99/repo/trateg/myvault/app/vault`

##  data import 

*Bybit Feed*
Bybit feed does not need credentials. It has data since 2018-11 for BTC and ETH.

*Alphavantage Feed*
Alphavantage needs an api key (you can get it free and fast on their website)
The creds file in `app/vault/trateg/creds.edn` has to contain your alphavantage api key: `{:alphavantage "your-alphavantage-api-key"}`
Alphavantage can download 5 symbols a minute. We have 40 demo symbols, so this will take a while.

*import single series*

MYVAULT environment variable needs to be set.

- alphavantage: `cd app/demo && clj -X:run :task :import-series :symbols "MSFT" :provider :alphavantage` 
- bybit:  `clj -X:run :task :import-series :symbols "BTCUSD" :provider :bybit`
- kibot:  `clj -X:run :task :import-series :symbols "NG" :provider :kibot`





## Tradingview Chart Study maker

`cd profiles/tradingview && clj -X:make-demo-charts`

Generated charts are stored in profiles/resources/tradingview-charts
and can be seen in goldlydocs web app in developer tools / pages / tradingview

*gann maker** `clj -X:run :task :gann` 
This reads profiles/resources/gann.edn and creates tradingview charts for each symbol in it.
The charts can be loaded via the tradingview page.
 