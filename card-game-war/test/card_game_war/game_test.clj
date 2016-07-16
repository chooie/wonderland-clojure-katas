(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]
            [card-game-war.cards :refer :all]))

(deftest test-play-round
  (testing "Correctly removes card from hand of cards"
    (is (= [ace-of-spades two-of-spades]
           (remove-card-from-cards [ace-of-spades two-of-hearts two-of-spades]
                                   two-of-hearts)))
    (is (thrown? java.lang.AssertionError
                 (remove-card-from-cards [ace-of-spades two-of-hearts]
                                         :non-existent))))
  (testing "Gets winning card from cards"
    (is (= ace-of-hearts
           (get-winning-card [ace-of-spades ace-of-hearts two-of-hearts
                              two-of-spades]))))
  #_(testing "Winner has other players' cards placed at the bottom of his deck"
    (is (= [[] [two-of-spades ace-of-spades]]
           (play-round [[ace-of-spades] [two-of-spades]])))))

(deftest test-play-game
  (testing "the player loses when they run out of cards"))
