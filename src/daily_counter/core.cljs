(ns daily-counter.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf :refer [dispatch dispatch-sync]]
            [daily-counter.events] ;; These two are only required to make the compiler
            [daily-counter.subs]   ;; load them
            [daily-counter.views]
            [devtools.core :as devtools]))


(devtools/install!)       ;; https://github.com/binaryage/cljs-devtools
(enable-console-print!)   ;; so that println writes to `console.log`


(defn render
  []
  (reagent/render [daily-counter.views/app]
                  (.getElementById js/document "app")))


(defn ^:export main
  []
  (dispatch-sync [:initialise-db])
  (render))


(defn ^:dev/after-load clear-cache-and-render!
  []
  ;; The `:dev/after-load` metadata causes this function to be called
  ;; after shadow-cljs hot-reloads code. We force a UI update by clearing
  ;; the Reframe subscription cache.
  (rf/clear-subscription-cache!)
  (main))
