(ns schuleinzugsgebiete.endpoint.static-pages
  (:require [schuleinzugsgebiete.pages :as pages]
            [compojure.core :refer [GET]]
            [ring.util.response :as response :refer [response]]
            [integrant.core :as ig]))

(defn- static-pages-endpoint []
  (GET "/" []
    (-> (pages/home)
        (response)
        (response/content-type "text/html"))))

(defmethod ig/init-key ::static-pages [_ _]
  (static-pages-endpoint))
