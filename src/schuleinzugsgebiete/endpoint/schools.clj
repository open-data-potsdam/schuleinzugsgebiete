(ns schuleinzugsgebiete.endpoint.schools
  (:require [schuleinzugsgebiete.boundary.schools :as schools]
            [compojure.core :refer [GET]]
            [ring.util.response :refer [response]]
            [ring.util.codec :as codec]
            [selmer.parser :as templates]
            [integrant.core :as ig]))

(defn render-school
  [school]
  (->> school
       (assoc {} :school)
       (templates/render-file "templates/school.html")))

(defn- schools-endpoint
  [{:keys [repo]}]
  (GET "/schulen/:school-name.html" [school-name]
    (->> (codec/url-decode school-name)
         (schools/by-name repo)
         (render-school)
         (response))))

(defmethod ig/init-key ::endpoint
  [_ options]
  (schools-endpoint options))
