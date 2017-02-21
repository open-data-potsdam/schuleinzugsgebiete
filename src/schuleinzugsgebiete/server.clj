(ns schuleinzugsgebiete.server
  (:require [schuleinzugsgebiete.pages :as pages]
            [integrant.core :as ig]
            [compojure.core :as c :refer [GET]]
            [compojure.route :as route]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.util.response :refer [response]]
            [ring.util.codec :as codec]))

(defn routes
  "Routing for test server."
  []
  (c/routes
   (GET "/" []
     (->> (pages/home)
          (response)))
   (GET "/schule/:name.html" [name]
     (->> (codec/url-decode name)
          (pages/school)
          (response)))
   (route/resources "/assets/")))

(defmethod ig/init-key ::handler
  [_ _]
  (routes))

(defmethod ig/init-key ::jetty
  [_ {:keys [handler] :as opts}]
  (run-jetty handler (-> opts (dissoc :handler) (assoc :join? false))))

(defmethod ig/halt-key! ::jetty
  [_ server]
  (.stop server))
