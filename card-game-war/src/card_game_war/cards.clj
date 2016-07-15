(ns card-game-war.cards)

;; Data Model

(def suits-by-ascending-rank [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])

(defn get-index-of-suit
  [suit]
  (let [index (.indexOf suits-by-ascending-rank suit)]
    (if (< index 0)
      (throw (Exception. "Suit doesn't exist"))
      index)))

(defn get-index-of-rank
  [rank]
  (let [index (.indexOf ranks rank)]
    index
    (if (< index 0)
      (throw (Exception. "Rank doesn't exist"))
      index)))

(def cards
  (into [] (for [suit suits-by-ascending-rank
                 rank ranks]
             {:suit suit :rank rank})))

(def number-of-cards-in-a-suit 13)

(defn- get-index-of-beginning-of-suit
  [suit]
  (let [suit-rank-index (get-index-of-suit suit)
        beginning-of-suit-in-cards (* suit-rank-index
                                      number-of-cards-in-a-suit)]
    beginning-of-suit-in-cards))


(defn- get-index-of-card
  [suit rank]
  (let [beginning-of-suit (get-index-of-beginning-of-suit suit)
        rank-index (get-index-of-rank rank)
        index-of-card (+ beginning-of-suit rank-index)]
    index-of-card))

(defn- get-card-at-index
  [index]
  (get cards index))

;; Cards

(def two-of-spades (get-card-at-index (get-index-of-card :spade 2)))
(def ace-of-spades (get-card-at-index (get-index-of-card :spade :ace)))
(def two-of-hearts (get-card-at-index (get-index-of-card :heart 2)))
(def ace-of-hearts (get-card-at-index (get-index-of-card :heart :ace)))
