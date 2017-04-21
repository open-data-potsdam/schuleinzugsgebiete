(ns schuleinzugsgebiete.component.xlsx
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
