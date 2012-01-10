(ns daruma.models.user
  (:require [noir.session :as session]
            [noir.util.crypt :as crypt]
            [noir.validation :as vali]
            [daruma.models.post :as posts]))

(defn admin? []
  (session/get :admin))

(defn login! [{:keys [username password] :as user}]
  (let [{stored-pass :password} (posts/get-user username)]
    (if (and stored-pass
             (crypt/compare password stored-pass))
      (do
        (session/put! :admin true)
        (session/put! :username username))
      (vali/set-error :username "Invalid pass"))))