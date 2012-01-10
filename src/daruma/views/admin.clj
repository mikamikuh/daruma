(ns daruma.views.admin
  (:use noir.core
        hiccup.core
        hiccup.page-helpers
        hiccup.form-helpers)
  (:require [noir.session :as session]
            [noir.validation :as vali]
            [noir.response :as resp]
            [clojure.string :as string]
            [daruma.views.common :as common]
            [daruma.models.post :as posts]
            [daruma.models.user :as users]))

(defpartial error-text [errors]
  [:p (string/join "<br />" errors)])

(defpartial user-fields [{:keys [username] :as usr}]
  (vali/on-error :username error-text)
  (text-field {:placeholder "Username"} :username username)
  (password-field {:placeholder "Password"} :password))

(defpartial post-fields [{:keys [title body]}]
  (vali/on-error :title error-text)
  (text-field {:placeholder "Title"} :title title)
  (vali/on-error :body error-text)
  (text-area {:placeholder "Body"} :body body))

(defpage "/login" {:as user}
  (if (users/admin?)
    (resp/redirect "/admin")
    (common/main-layout
     (posts/get-info)
     (form-to [:post "/login"]
              [:ul.actions
               [:li (link-to {:class "submit"} "/" "Login")]]
              (user-fields user)
              (submit-button {:class "submit"} "submit")))))

(defpage [:post "/login"] {:as user}
  (if (users/login! user)
    (resp/redirect "/admin")
    (render "/login" user)))

(defpage "/admin" {:as post}
  (common/main-layout
   (form-to [:post "/admin"]
            [:ul.actions
             [:li (link-to {:class "submit"} "/" "Add")]]
            (post-fields post)
            (submit-button {:class "submit"} "add post"))))

(defpage [:post "/admin"] {:as post}
  nil)