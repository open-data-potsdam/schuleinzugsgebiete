(ns schuleinzugsgebiete.pages
  (:require [schuleinzugsgebiete.data :as data]
            [selmer.parser :as templates]))

(defn home
  "Renders the home page."
  []
  (->> data/schools
       (assoc {} :schools)
       (templates/render-file "templates/home.html")))

(defn school
  "Renders the pages of the school with the given name. The name should be url
   encoded."
  [name]
  (->> (data/find-school-by-name name)
       (templates/render-file "templates/school.html")))
