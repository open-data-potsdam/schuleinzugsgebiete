(ns schuleinzugsgebiete.main
  "The main entry point of the application."
  (:gen-class)
  (:require [clojure.java.io :as io]
            [duct.core :as duct]))

(defn -main
  "This function is called by the JVM on production."
  [& args]
  (duct/exec (duct/read-config (io/resource "schuleinzugsgebiete/config.edn"))))
