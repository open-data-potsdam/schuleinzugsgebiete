(ns schuleinzugsgebiete.endpoint.home
  (:require [schuleinzugsgebiete.boundary.schools :as schools]
            [compojure.core :refer [GET]]
            [ring.util.response :as response :refer [response]]
            [ring.util.codec :as codec]
            [selmer.parser :as templates]
            [integrant.core :as ig]))

(defn- render
  [schools]
  (->> schools
       (map #(assoc % :url-name (codec/url-encode (:name %))))
       (assoc {} :schools)
       (templates/render-file "templates/home.html")))

(defn- home-endpoint
  [{:keys [repo]}]
  (GET "/" []
    (-> (schools/all repo)
        (render)
        (response)
        (response/content-type "text/html"))))

(defmethod ig/init-key ::endpoint
  [_ options]
  (home-endpoint options))
