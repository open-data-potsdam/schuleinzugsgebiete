(ns user
  (:require [clojure.repl :refer :all]
            [clojure.pprint :refer [pprint]]
            [clojure.tools.namespace.repl :refer [refresh]]
            [integrant.repl :refer [system clear go reset]]
            [integrant.core :as ig]
            [schuleinzugsgebiete.server :as server]))

(def config
  {::server/jetty {:port 8080
                   :handler (ig/ref ::server/handler)}
   ::server/handler nil})

(integrant.repl/set-prep! (constantly config))
