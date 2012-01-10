(ns daruma.views.blog
  (:require [daruma.models.post :as posts]
            [daruma.models.user :as users]
            [daruma.views.common :as common]
            [noir.response :as resp]
            [noir.content.getting-started])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/" []
  (common/main-layout (posts/get-info)
                      (map common/article (posts/get-page 1))))