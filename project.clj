(defproject schuleinzugsgebiete "1.0.0-SNAPSHOT"
  :description "School districts in Potsdam"
  :url "https://github.com/open-data-potsdam/schuleinzugsgebiete"

  :license {:name "Apache License version 2.0"
            :url "http://www.apache.org/licenses/"}

  :min-lein-version "2.0.0"

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [dk.ative/docjure "1.11.0"]
                 [selmer "1.10.5"]
                 [compojure "1.5.2"]
                 [ring/ring-codec "1.0.1"]
                 [ring/ring-jetty-adapter "1.5.1"]
                 [integrant "0.2.0"]]

  :plugins [[lein-shell "0.5.0"]]

  :profiles {:dev {:dependencies [[integrant/repl "0.1.0"]]
                   :source-paths ["dev"]}}

  :aliases {"build" ["do"
                     ["shell" "gulp"]
                     ["run" "-m" "schuleinzugsgebiete.generator" "site"]
                     ["shell" "cp" "-R" "resources/public" "site/assets"]]})
