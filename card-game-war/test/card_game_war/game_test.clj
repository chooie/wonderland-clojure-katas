(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]
            [card-game-war.cards :refer :all]))

(deftest test-query
  (testing "Correctly gets values of cards"
    (is (= :spade (get-card-suit two-of-spades)))
    (is (= 2 (get-card-rank two-of-spades)))
    (is (= :ace (get-card-rank ace-of-spades)))))

(deftest test-comparison
  (testing "Correctly compares cards"
    ))

(deftest test-play-round
  (testing "the highest rank wins the cards in the round")
  (testing "queens are higher rank than jacks")
  (testing "kings are higher rank than queens")
  (testing "aces are higher rank than kings")
  (testing "if the ranks are equal, clubs beat spades")
  (testing "if the ranks are equal, diamonds beat clubs")
  (testing "if the ranks are equal, hearts beat diamonds"))

(deftest test-play-game
  (testing "the player loses when they run out of cards"))
