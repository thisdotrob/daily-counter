(ns daily-counter.views
  (:require [daily-counter.dates :as dates]
            [reagent.core  :as reagent]
            [re-frame.core :refer [subscribe dispatch]]))

(defn navbar [ter-name ter-id date-range-start
              date-range-end user-email]
  [:nav.navbar.navbar-expand-sm.navbar-light.bg-light
   [:a.navbar-brand {:href "/"} "Daily counter"]
   [:ul.navbar-nav.mr-auto
    [:li.nav-item.mr-3
     [:button.btn.btn-primary "<"]]
    [:li.nav-item
     [:button.btn.btn-primary ">"]]]
   [:div.nav-item
    [:span "?"]]])

(defn alerts-section [alerts]
  (when (seq alerts)
    (for [a alerts]
      ^{:key (:id a)}
      [:div.alert
       {:role "alert",
        :class (if (= (:type a) :error)
                 "alert-warning"
                 "alert-success")}
       (:text a)
       [:button.close
        {:type "button" :on-click #(dispatch [:dismiss-alert (:id a)])}
        [:i.fa.fa-close]]])))

(defn app []
  (let [alerts @(subscribe [:alerts])
        today @(subscribe [:today])]
    [:div.container
     [navbar]
     [alerts-section alerts]
     [:span today]]))
