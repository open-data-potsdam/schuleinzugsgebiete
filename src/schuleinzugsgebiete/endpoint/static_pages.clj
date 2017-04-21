(ns schuleinzugsgebiete.endpoint.static-pages
  (:require [schuleinzugsgebiete.boundary.schools :as schools]
            [compojure.core :refer [GET]]
            [ring.util.response :as response :refer [response]]
            [ring.util.codec :as codec]
            [selmer.parser :as templates]
            [integrant.core :as ig]))

(defn render-home
  [schools]
  (->> schools
       (map #(assoc % :url-name (codec/url-encode (:name %))))
       (assoc {} :schools)
       (templates/render-file "templates/home.html")))

(defn- static-pages-endpoint
  [{:keys [repo]}]
  (GET "/" []
    (-> (schools/all repo)
        (render-home)
        (response)
        (response/content-type "text/html"))))

(defmethod ig/init-key ::static-pages
  [_ options]
  (static-pages-endpoint options))
