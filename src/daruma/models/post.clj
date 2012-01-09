(ns daruma.models.post
  (:require [clj-time.core :as clt-core]
            [clj-time.format :as clt-format])
  (:use somnium.congomongo))

(def posts-per-page 10)
(def date-format (clt-format/formatter "yy/MM/dd" (clt-core/default-time-zone)))


(def conn
  (make-connection "blog"
                   :host "127.0.0.1"
                   :port 27017))

(set-connection! conn)

(defn get-page [page]
  (let [page-num (dec page)
        prev (* page-num posts-per-page)]
    (fetch :posts :limit posts-per-page :skip prev)))

(defn add! [title body]
  (insert! :posts {:title title
                   :body body
                   :date (clt-format/unparse date-format (clt-core/now))}))