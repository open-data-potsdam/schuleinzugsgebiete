(ns schuleinzugsgebiete.server
  (:require [schuleinzugsgebiete.data :as data]
            [integrant.core :as ig]
            [selmer.parser :as templates]
            [compojure.core :as c :refer [GET]]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.util.response :refer [response]]))

(defn routes
  "Routing for test server."
  []
  (c/routes
   (GET "/" []
     (->> data/schools
          (templates/render-file "templates/home.html")
          (response)))
   (GET "/schule/:name" [name]
     (->> (data/find-school-by-name name)
          (templates/render-file "templates/school.html")
          (response)))))

(defmethod ig/init-key ::handler
  [_ _]
  (routes))

(defmethod ig/init-key ::jetty
  [_ {:keys [handler] :as opts}]
  (run-jetty handler (-> opts (dissoc :handler) (assoc :join? false))))

(defmethod ig/halt-key! ::jetty
  [_ server]
  (.stop server))
