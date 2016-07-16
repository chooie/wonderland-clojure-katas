(ns card-game-war.game
  (:require [card-game-war.card-query :as c-q]
            [card-game-war.cards :as cards]
            [card-game-war.deal :as deal]))

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

(defn put-cards-at-bottom-of-players-deck
  [deck cards]
  (conj deck cards))

(defn play-round
  [player-hands]
  (let [cards (mapv first player-hands)
        winning-card (get-winning-card cards)
        winning-card-index (.indexOf cards winning-card)
        winning-player-index winning-card-index
        cards-less-winning-card (remove-card-from-cards cards winning-card)
        cards-to-place-at-bottom-of-deck (into []
                                               (cons winning-card
                                                     cards-less-winning-card))
        player-hands-less-hand (mapv (fn [hand]
                                       (into [] (rest hand)))
                                     player-hands)
        winning-player-deck (get player-hands-less-hand winning-player-index)
        new-winning-player-deck (vec (concat winning-player-deck
                                             cards-to-place-at-bottom-of-deck))
        new-player-decks (assoc player-hands-less-hand
                                winning-player-index
                                new-winning-player-deck)]
    new-player-decks))

(defn one-player-remaining?
  [player-hands]
  (let [hands-with-cards-left (filterv (fn [hand] (> (count hand) 0))
                                       player-hands)]
    (= (count hands-with-cards-left) 1)))

(defn get-winning-player-index
  [player-decks]
  {:pre [(one-player-remaining? player-decks)]}
  (let [not-empty? (complement empty?)
        non-empty-deck (first (filter not-empty? player-decks))]
    (.indexOf player-decks non-empty-deck)))

(defn play-hands-recursive
  [player-decks]
  (loop [states [player-decks]]
    (let [latest-deck (last states)]
      (if (one-player-remaining? latest-deck)
        (let [winning-player-index (get-winning-player-index latest-deck)]
          [states (str "Player " (inc winning-player-index) " Wins")])
        (let [new-player-decks (play-round latest-deck)]
          (recur (conj states new-player-decks)))))))

(defn play-game
  [number-of-players]
  (let [shuffled-deck (shuffle cards/cards)
        starting-player-decks (deal/deal-cards shuffled-deck number-of-players)]
    (play-hands-recursive starting-player-decks)))
