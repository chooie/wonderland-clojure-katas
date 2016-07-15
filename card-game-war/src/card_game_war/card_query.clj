(ns card-game-war.card-query
  (:require [card-game-war.cards :as c]))

(defn get-card-suit
  [{:keys [suit]}]
  suit)

(defn get-card-rank
  [{:keys [rank]}]
  rank)

(defn rank-is-greater?
  [{rank1 :rank :as card-to-check} {rank2 :rank :as other-card}]
  (let [rank1-index (c/get-index-of-rank rank1)
        rank2-index (c/get-index-of-rank rank2)]
    (> rank1-index rank2-index)))

(defn ranks-are-equal?
  [{rank1 :rank :as card-to-check} {rank2 :rank :as other-card}]
  (let [rank1-index (c/get-index-of-rank rank1)
        rank2-index (c/get-index-of-rank rank2)]
    (= rank1-index rank2-index)))

(defn suit-is-greater?
  [{rank1 :rank suit1 :suit :as card-to-check}
   {rank2 :rank suit2 :suit :as other-card}]
  {:pre [(= rank1 rank2) (not= suit1 suit2)]}
  (let [suit1-index (c/get-index-of-suit suit1)
        suit2-index (c/get-index-of-suit suit2)]
    (> suit1-index suit2-index)))

(defn card-wins?
  [card-to-check other-card]
  {:pre [(not= card-to-check other-card)]}
  (if (rank-is-greater? card-to-check other-card)
    true
    (if (ranks-are-equal? card-to-check other-card)
      (suit-is-greater? card-to-check other-card)
      false)))
