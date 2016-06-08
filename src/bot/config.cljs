(ns bot.config
  (:require [cljs.nodejs :as nodejs]))

(def tmi (js/require "tmi.js"))

(def ^:private options
  (clj->js
   {:options    {:debug "true"}
    :connection {:cluster "aws"
                 :reconnect "true"}
    :identity   {:username "llamautomaton"
                 :password "oauth:qq8v7ljmgmo3w1yg0gy18qq2omp1nx"}
    :channels   ["#st00sh"]}))

(def llama (.client tmi options))
