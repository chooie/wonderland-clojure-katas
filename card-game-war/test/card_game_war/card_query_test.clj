(ns card-game-war.card-query-test
  (:require [clojure.test :refer :all]
            [card-game-war.cards :refer :all]
            [card-game-war.card-query :refer :all]))

(deftest test-query
  (testing "Correctly gets values of cards"
    (is (= :spade (get-card-suit two-of-spades)))
    (is (= :heart (get-card-suit two-of-hearts)))
    (is (= 2 (get-card-rank two-of-spades)))
    (is (= :ace (get-card-rank ace-of-spades))))
  (testing "Compares two cards"
    (is (true? (card-wins? ace-of-spades two-of-spades)))
    (is (true? (card-wins? ace-of-hearts ace-of-spades)))
    (is (true? (card-wins? ace-of-spades two-of-hearts)))))

