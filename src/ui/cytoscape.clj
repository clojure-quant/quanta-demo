(ns ui.cytoscape)

(defn cytoscape [opts]
  (with-meta
    opts
    {:render-fn 'ui.cytoscape/cytoscape}))

 