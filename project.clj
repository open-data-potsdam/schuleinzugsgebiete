(defproject schuleinzugsgebiete "1.0.0-SNAPSHOT"
  :description "School districts in Potsdam"
  :url "https://github.com/open-data-potsdam/schuleinzugsgebiete"

  :license {:name "Apache License version 2.0"
            :url "http://www.apache.org/licenses/"}

  :min-lein-version "2.0.0"

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [duct/core "0.1.1"]
                 [duct/module.logging "0.1.1"]
                 [duct/module.web "0.1.2"]
                 [dk.ative/docjure "1.11.0"]
                 [selmer "1.10.7"]]

  :plugins [[duct/lein-duct "0.9.0-alpha2"]]

  :main ^:skip-aot schuleinzugsgebiete.main

  :duct {:config-paths ["resources/schuleinzugsgebiete/config.edn"]}

  :resource-paths ["resources" "target/resources"]

  :prep-tasks     ["javac" "compile" ["duct" "compile"]]

  :profiles
  {:dev     [:project/dev :profiles/dev]
   :repl    {:prep-tasks   ^:replace ["javac" "compile"]
             :repl-options {:init-ns user}}
   :uberjar {:aot :all}
   :profiles/dev {}
   :project/dev  {:source-paths   ["dev/src"]
                  :resource-paths ["dev/resources"]
                  :dependencies   [[integrant/repl "0.2.0"]
                                   [eftest "0.3.0"]
                                   [kerodon "0.8.0"]]}})
