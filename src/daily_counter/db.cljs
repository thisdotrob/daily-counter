(ns daily-counter.db
  (:require [daily-counter.dates :as dates]
            [cljs.spec.alpha :as s]))


(s/def ::type keyword?)
(s/def ::text string?)
(s/def ::id uuid?)
(s/def ::alerts (s/coll-of (s/keys ::req-un [::type ::text ::id])))

(s/def ::date (s/and string? dates/date-ISO-str?))
(s/def ::today ::date)

(s/def ::db (s/keys :req-un [::alerts ::today]))
