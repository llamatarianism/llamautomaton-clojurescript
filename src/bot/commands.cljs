(ns bot.commands
  (:require [cljs.nodejs :as nodejs]
            [clojure.string :as st]
            [bot.config :refer [llama]]))

(defn handle-commands
  "Inspects messages from users and executes a different function
   depending on what command is used."
  [chan user mesg self]
  (let [words (st/split mesg #" ")
        command (first words)]
    (when (= (first command) \!)
      (case (subs command 1)
        "ryanrje" (screw-ryan chan user)
        "8-ball" (eightball chan)))))

(defn- screw-ryan
  "If the user is Ryan, calls him an asshat. Otherwise, responds with a random Ryan-related image."
  [chan user]
  (let [ryan-links ["http://i.imgur.com/NPFvSf6.png"
                    "http://i.imgur.com/CrZ4BgG.jpg"
                    "http://i.imgur.com/5oanuKD.jpg"
                    "http://i.imgur.com/ZgD16X4.png"]]
    (if (= (.-username user) "ryanrje")
      (.say llama chan "Damn right, asshat.")
      (.say llama chan (ryan-links (Math/floor (int (rand 5))))))))

(defn- eightball
  "A magic 8-ball command. Generates a random responss out of a list of possibilities."
  [chan]
  (.say
   llama
   chan
   (["It is certain."
     "It is decidedly so."
     "Without a doubt."
     "Yes, definitely."
     "You may rely on it."
     "As I see it, yes."
     "Most likely."
     "Outlook good."
     "Yes."
     "Signs point to yes."
     "Reply hazy. Try again."
     "Ask again later."
     "Better not tell you now."
     "Cannot predict now."
     "Concentrate and ask again."
     "Don't count on it."
     "My reply is: no."
     "My sources say: no."
     "Outlook not so good."
     "Very doubtful."
     "No."
     "Bugger off."]
    (int (Math/floor (rand 22))))))
