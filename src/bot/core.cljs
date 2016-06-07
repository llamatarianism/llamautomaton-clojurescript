(ns bot.core
  (:require [cljs.nodejs :as nodejs]))

(nodejs/enable-util-print!)

(def tmi (js/require "tmi.js"))

(def options
  (js-obj
   "options"    (js-obj "debug" true)
   "connection" (js-obj "cluster"   "aws"
                        "reconnect" true)
   "identity"   (js-obj "username" "llamautomaton"
                        "password" "foo")
   "channels"   (array)))

(def llama (.client tmi options))

(defn -main []
  (.connect llama)
  (println "yay"))

(set! *main-cli-fn* -main)
