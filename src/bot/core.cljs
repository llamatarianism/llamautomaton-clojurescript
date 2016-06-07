(ns bot.core
  (:require [cljs.nodejs :as nodejs]
            [clojure.string :as str]))

(nodejs/enable-util-print!)

(def tmi (js/require "tmi.js"))

(def options
  (js-obj
   "options"    (js-obj "debug" true)
   "connection" (js-obj "cluster"   "aws"
                        "reconnect" true)
   "identity"   (js-obj "username" "llamautomaton"
                        "password" "oauth:qq8v7ljmgmo3w1yg0gy18qq2omp1nx")
   "channels"   (array "#captaintheredbeard")))

(def llama (.client tmi options))

(defn- say-hello [chan user mesg self]
  (when (and (= (.-username user) "llamatarianism")
           (= mesg "WE CAN REBUILD HIM"))
    (.say llama chan "WE HAVE THE TECHNOLOGY")))

(defn -main []
  (.connect llama)
  (.on llama "chat" say-hello)
  (println "yay"))

(set! *main-cli-fn* -main)
