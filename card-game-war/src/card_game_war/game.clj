(ns card-game-war.game)

(defn get-card-suit
  [{:keys [suit]}]
  suit)

(defn get-card-rank
  [{:keys [rank]}]
  rank)

(defn shuffle-deck
  [deck]
  (shuffle deck))

(defn play-round [player1-card player2-card])

(defn play-game [player1-cards player2-cards])
