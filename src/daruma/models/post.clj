(ns daruma.models.post
  (:require [mongoika :as db]))

(def host "localhost")
(def port 27017)
(def blog :blog)

(defmacro with-db [queries]
  `(db/with-mongo [connection# {:host host :port port}]
     (db/with-db-binding (db/database connection# blog)
       ~queries)))


(defn add! [title body]
  (with-db (db/insert! :posts {:title title :body body})))