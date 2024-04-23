(ns quanta.notebook.dev.aot.bongo)

(gen-class :name demo.bongo.Hello)

(comment
  (compile 'demo.bongo)
  (demo.bongo.Hello.))
