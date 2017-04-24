(ns schuleinzugsgebiete.component.xlsx
  "Loads the data from xlsx files and serves them as Clojure data structures.
   Instead of loading and parsing the xlsx files everytime a page is rendered,
   the data is used from memory and the xlsx handling is done only during start
   up."
  (:require [dk.ative.docjure.spreadsheet :as xls]
            [integrant.core :as ig]))

(defn- load-xls-data
  [{:keys [file-name sheet column-mapping rows-to-drop]}]
  (->> file-name
       (xls/load-workbook-from-resource)
       (xls/select-sheet sheet)
       (xls/select-columns column-mapping)
       (drop rows-to-drop)))

(defmethod ig/init-key ::school-districts
  [_ options]
  (group-by :number (load-xls-data options)))

(defmethod ig/init-key ::schools
  [_ options]
  (load-xls-data options))
