(ns daily-counter.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
  :today
  (fn [db _] (:today db)))

(reg-sub
  :alerts
  (fn [db _] (:alerts db)))
