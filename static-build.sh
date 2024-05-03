#!/bin/sh
rm target -r
clojure -X:webly:static:web-static
clojure -X:webly:static-sci:web-static
clojure -X:nbeval-static:public-algos:my-algos:web-static