(ns schuleinzugsgebiete.main
    (:gen-class)
    (:require [schuleinzugsgebiete.component.site-generator :as gen]
              [clojure.java.io :as io]
              [duct.core :as duct]
              [integrant.core :as ig]))

(defn- load-system
  "Loads the system from the config and initializes it."
  []
  (-> "schuleinzugsgebiete/config.edn"
      (io/resource)
      (duct/read-config)
      (duct/prep)
      (ig/init)))

(defn -main [& args]
  (let [system (load-system)]
    (duct/add-shutdown-hook ::exec #(ig/halt! system))
    (let [generator (::gen/generator system)]
      (generator))))
