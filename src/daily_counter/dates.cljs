(ns daily-counter.dates
  (:require ["date-fns" :as date-fns]))

(defn date->ISO-str [d]
  (date-fns/formatISO d (clj->js {:representation "date"})))

(defn today! []
  (-> (js/Date.) date->ISO-str))

(defn date-ISO-str? [s]
  (-> s date-fns/parseISO date-fns/isValid))
