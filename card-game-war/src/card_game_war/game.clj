(ns card-game-war.game
  (:require [card-game-war.card-query :as c-q]))

(defn shuffle-deck
  [deck]
  (shuffle deck))

(defn card-beats-all-cards?
  [cards card-to-check]
  (let [card-loses? (complement c-q/card-wins?)
        cards-that-beat-card (filter (fn [card]
                                       (card-loses? card-to-check card))
                                     cards)]
    (= 0 (count cards-that-beat-card))))

(defn card-in-cards?
  [cards card]
  (let [card-index (.indexOf cards card)]
    (>= card-index 0)))

(defn remove-card-from-cards
  [cards card-to-remove]
  {:pre [(card-in-cards? cards card-to-remove)]}
  (filterv (fn [card] (not= card card-to-remove))
           cards))

(defn get-winning-card
  [cards]
  (let [results (mapv (fn [card]
                        (let [cards-less-card (remove-card-from-cards cards
                                                                      card)]
                          (card-beats-all-cards? cards-less-card card)))
                      cards)
        index-of-winner (.indexOf results true)
        winning-card (get cards index-of-winner)]
    winning-card))

(defn play-round
  [player-hands]
  (let [cards (mapv first player-hands)]
    cards))

(defn play-game [player1-cards player2-cards])
