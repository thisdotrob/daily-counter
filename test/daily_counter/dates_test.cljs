(ns daily-counter.dates-test
  (:require [daily-counter.dates :as sut]
            [cljs.test :refer (deftest is)]))

(deftest date-ISO-str?
  (is (= false
         (sut/date-ISO-str? "2020/02/02")))
  (is (= true
         (sut/date-ISO-str? "2020-12-31")))
  (is (= false
         (sut/date-ISO-str? "2019-13-01"))))
