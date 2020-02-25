(ns daily-counter.events
  (:require [daily-counter.db]
            [daily-counter.dates :as dates]
            [ajax.core :as ajax]
            [day8.re-frame.http-fx] ;; http-xhrio effect
            [re-frame.core :refer [reg-cofx reg-event-db reg-event-fx inject-cofx after]]
            [cljs.spec.alpha :as s]))

(defn check-and-throw
  "Throws an exception if `db` doesn't match the Spec `a-spec`."
  [a-spec db]
  (when-not (s/valid? a-spec db)
    (throw (ex-info (str "spec check failed: " (s/explain-str a-spec db)) {}))))

(def check-spec-interceptor (after (partial check-and-throw :daily-counter.db/db)))

(reg-cofx
  :today
  (fn [cofx _]
    (assoc cofx :today (dates/today!))))

(defn initialise-db [{:keys [today]} _]
  {:db {:today today
        :alerts []}})

(defn dismiss-alert [db [_ id]]
  (letfn [(f [alert] (= id (:id alert)))]
    (update db :alerts #(remove f %))))

(reg-event-fx
  :initialise-db
  [(inject-cofx :today)
   check-spec-interceptor]
  initialise-db)

(reg-event-db
  :dismiss-alert
  [check-spec-interceptor]
  dismiss-alert)
