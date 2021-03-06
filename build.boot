(set-env!
  :dependencies '[[adzerk/boot-cljs          "1.7.228-1"]
                  [adzerk/boot-reload        "0.4.7"]
                  [cljsjs/leaflet "0.7.7-2"]
                  [hoplon/boot-hoplon        "0.1.13"]
                  [hoplon/hoplon             "6.0.0-alpha14"]
                  [org.clojure/clojure       "1.7.0"]
                  [org.clojure/clojurescript "1.8.40"]
                  [tailrecursion/boot-jetty  "0.1.3"]]
  :source-paths #{"src"}
  :asset-paths  #{"assets"})

(require
  '[adzerk.boot-cljs         :refer [cljs]]
  '[adzerk.boot-reload       :refer [reload]]
  '[hoplon.boot-hoplon       :refer [hoplon prerender]]
  '[tailrecursion.boot-jetty :refer [serve]])

(deftask dev
  "Build hoplon-leaflet for local development."
  []
  (comp
   (sift :add-jar {'cljsjs/leaflet #"\.css$"})
    (watch)
    (speak)
    (hoplon)
    (reload)
    (cljs)
    (serve :port 8000)
    (target)))

(deftask prod
  "Build hoplon-leaflet for production deployment."
  []
  (comp
    (hoplon)
    (cljs :optimizations :advanced)
    (prerender)))
