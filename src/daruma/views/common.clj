(ns daruma.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page-helpers :only [include-css html5]]))

(defpartial main-layout [info content]
            (html5
              [:head
               [:title "daruma"]]
              [:body
               [:div#wrapper
                [:div.content
                 [:div#header
                  [:h1 (info :title)]]
                 [:div#content
                  (map article content)]]]]))

(defpartial article [{:keys [title body date]}]
  [:article
   [:h2 title]
   [:div {:class "body"} body]
   [:div {:class "info"} date]])