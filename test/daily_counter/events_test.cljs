(ns daily-counter.events-test
  (:require [cljs.test :refer (deftest is)]
            [daily-counter.events :as sut]))

(deftest initialise-db
  (let [coeffects {:today "2020-02-01"}
        event {}]
    (is (= "2020-02-01"
           (-> coeffects
               (sut/initialise-db event)
               :db
               :today)))))

(deftest dismiss-alert
  (let [id (random-uuid)
        alert {:id id
               :text "Some text"
               :type :error}
        other-id (random-uuid)
        other-alert (assoc alert :id other-id)
        current-db {:alerts [alert other-alert]}
        event nil]
    (is (= 1
           (->> [event id]
                (sut/dismiss-alert current-db)
                :alerts
                count)))
    (is (= other-id
           (->> [event id]
                (sut/dismiss-alert current-db)
                :alerts
                first
                :id)))))
