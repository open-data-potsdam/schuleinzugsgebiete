(ns schuleinzugsgebiete.boundary.schools
  (:require [integrant.core :as ig]))

(defprotocol Schools
  (all [this])
  (by-name [this name]))

(defn- create-xlsx-repo
  [{:keys [schools]}]
  (reify Schools
    (all [_]
      schools)
    (by-name [_ name]
      (->> schools
           (filter #(= (:name %) name))
           (first)))))

(defmethod ig/init-key ::xlsx-repo
  [_ options]
  (create-xlsx-repo options))
