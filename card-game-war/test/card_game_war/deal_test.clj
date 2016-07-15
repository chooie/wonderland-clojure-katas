(ns card-game-war.deal-test
  (:require [clojure.test :refer :all]
            [card-game-war.deal :refer :all]
            [card-game-war.cards :refer :all]))

(deftest test-deal
  (testing "Deals cards to players"
    (let [number-of-players 2]
      (let [deck [ace-of-spades two-of-spades ace-of-hearts two-of-hearts]]
        (is (= [[ace-of-spades ace-of-hearts] [two-of-spades two-of-hearts]]
               (deal-cards deck number-of-players))))
      (let [player-hands (deal-cards cards number-of-players)
            player-1-hand (get player-hands 0)
            player-1-hand-length (count player-1-hand)
            player-2-hand (get player-hands 0)
            player-2-hand-length (count player-1-hand)]
        (is (= player-1-hand-length player-2-hand-length))))))
