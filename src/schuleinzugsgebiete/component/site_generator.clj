(ns schuleinzugsgebiete.component.site-generator
  (:require [schuleinzugsgebiete.boundary.schools :as schools]
            [clojure.string :as str]
            [clojure.java.io :as io]
            [compojure.core :as c]
            [ring.util.codec :as codec]
            [integrant.core :as ig]))

(defn- clean
  "Cleans the given directory by deleting the whole content recursively."
  [dir]
  (when (.exists (io/as-file dir))
    (doseq [file (-> (io/file dir)
                     (file-seq)
                     (reverse))]
      (io/delete-file file))))

(defn- all-sites
  [repo]
  (->> (schools/all repo)
       (map :name)
       (map codec/url-encode)
       (map #(str "/schulen/" % ".html"))
       (cons "/")))

(defn- path->file
  "When path is a directory, the file must be <path>/index.html."
  [path]
  (if-not (str/ends-with? path ".html")
    (str path "/index.html")
    path))

(defn- generate-and-save
  "Fetches the given path from the ring handler function and saves it as a HTML
   file to the given target directory."
  [path ring-handler-fn target-dir]
  (let [file (str target-dir (path->file path))]
    (io/make-parents file)
    (->> {:uri path :request-method :get}
         (ring-handler-fn)
         :body
         (spit file))))

(defn- generate-site
  "Runs over all pages of the web app and persists them as HTML files in the
   given directory. Before the directory is cleaned up."
  [{:keys [repo dir static-pages-endpoint schools-endpoint]}]
  (clean dir)
  (let [ring-handler (c/routes static-pages-endpoint schools-endpoint)
        paths (all-sites repo)]
    (doseq [path paths]
      (generate-and-save path ring-handler dir))))

(defmethod ig/init-key ::generator
  [_ options]
  #(generate-site options))
