(defproject daily-counter "1.0.0"
  :dependencies [[org.clojure/clojure        "1.10.1"]
                 [org.clojure/clojurescript  "1.10.597"
                  :exclusions [com.google.javascript/closure-compiler-unshaded
                               org.clojure/google-closure-library
                               org.clojure/google-closure-library-third-party]]
                 [thheller/shadow-cljs       "2.8.83"]
                 [day8.re-frame/http-fx      "v0.2.0"]
                 [cljs-ajax                  "0.8.0"]
                 [reagent                    "0.9.1"]
                 [re-frame                   "0.11.0"]
                 [binaryage/devtools         "1.0.0"]
                 [day8.re-frame/tracing      "0.5.3"]]
  :plugins [[lein-shadow                "0.1.7"]]
  :clean-targets ^{:protect false} [:target-path
                                    "shadow-cljs.edn"
                                    "package.json"
                                    "package-lock.json"
                                    "./resources/public/js"]
  :shadow-cljs {:nrepl {:port 8777}
                :source-paths ["src" "test"]
                :builds {:client {:target :browser
                                  :output-dir "resources/public/js"
                                  :devtools {:http-root "resources/public"
                                             :http-port 8280}
                                  :modules {:client {:init-fn daily-counter.core/main}}}
                         :test {:target :karma
                                :output-to "resources/tests.js"}}})

