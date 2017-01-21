(ns schuleinzugsgebiete.data
  "Provides access to the data. The data is taken as an Excel file as it is
   published by the city of Potsdam."
  (:require [dk.ative.docjure.spreadsheet :as xls]))

(defonce school-districts
  (->> "SchuleinzugsbereicheSeptember2016.xlsx"
       (xls/load-workbook-from-resource)
       (xls/select-sheet "Zusammengefasst")
       (xls/select-columns {:A :street :B :area :C :number})
       (drop 2)
       (group-by :number)))

(defonce schools
  (->> "Grundschulen.xlsx"
       (xls/load-workbook-from-resource)
       (xls/select-sheet "Grundschulen")
       (xls/select-columns {:A :name :B :address :C :type :D :number})
       (drop 1)))

(defn find-school-by-name
  "Returns the first school with the given name."
  [name]
  (->> schools
       (filter #(= (:name %) name))
       (first)))

(defn find-addresses-by-school
  "Returns a list of all address ranges for the given school number."
  [number]
  (get school-districts number))
