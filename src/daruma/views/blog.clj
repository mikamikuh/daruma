(ns daruma.views.blog
  (:require [daruma.models.post :as posts]
            [daruma.views.common :as common]
            [noir.content.getting-started])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/" []
  (posts/get-page 1))