(ns bot.core
  (:require [cljs.nodejs :as nodejs]
            [bot.config :refer [llama]]
            [bot.commands :refer [handle-commands]]))

(nodejs/enable-util-print!)

(defn -main []
  (.connect llama)
  (.on llama "chat" handle-commands)
  (println "yay"))

(set! *main-cli-fn* -main)
