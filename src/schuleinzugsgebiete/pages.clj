(ns schuleinzugsgebiete.pages
  (:require [schuleinzugsgebiete.data :as data]
            [selmer.parser :as templates]
            [ring.util.codec :as codec]))

(defn- encode-name
  [{:keys [name] :as m}]
  (let [encoded (codec/url-encode name)]
    (assoc m :url-name encoded)))

(defn home
  "Renders the home page."
  []
  (->> data/schools
       (map encode-name)
       (assoc {} :schools)
       (templates/render-file "templates/home.html")))

(defn school
  "Renders the pages of the school with the given name. The name should be url
   encoded."
  [name]
  (->> (data/find-school-by-name name)
       (assoc {} :school)
       (templates/render-file "templates/school.html")))
