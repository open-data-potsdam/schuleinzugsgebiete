(ns schuleinzugsgebiete.generator
  (:require [schuleinzugsgebiete.pages :as pages]
            [schuleinzugsgebiete.data :as data]
            [clojure.java.io :as io])
  (:gen-class))

(defn- clean
  "Cleans the given directory by deleting all contents."
  [dir]
  (when (.exists (io/as-file dir))
    (doseq [file (-> (io/file dir)
                     (file-seq)
                     (reverse))]
      (io/delete-file file))))

(defn- write-to
  "Writes the given content to the given file and ensures, the parent
   directories exist."
  [content file]
  (io/make-parents file)
  (spit file content))

(defn -main
  "Generates all pages as HTML files and puts them into the given directory.
   Assets like CSS and JS will also be copied to the directory.

   If the directory already exists, it will be deleted and recreated."
  [dir]
  (clean dir)
  (let [home (pages/home)]
    (write-to home (str dir "/index.html"))
    (doseq [{:keys [name]} data/schools
            :let [school (pages/school name)]]
      (write-to school (str dir "/schule/" name ".html")))))
