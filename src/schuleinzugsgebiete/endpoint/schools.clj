(ns schuleinzugsgebiete.endpoint.schools
  (:require [schuleinzugsgebiete.pages :as pages]
            [compojure.core :refer [GET]]
            [ring.util.response :refer [response]]
            [ring.util.codec :as codec]
            [integrant.core :as ig]))

(defn- schools-endpoint []
  (GET "/schulen/:school-name.html" [school-name]
    (-> (codec/url-decode school-name)
        (pages/school)
        (response))))

(defmethod ig/init-key ::schools [_ _]
  (schools-endpoint))
