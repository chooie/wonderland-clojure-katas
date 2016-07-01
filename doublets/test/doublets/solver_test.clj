(ns doublets.solver-test
  (:require [clojure.test :refer :all]
            [doublets.solver :refer :all]))

(deftest solver-test
  ;;   (is (= ["wheat" "cheat" "cheap" "cheep" "creep" "creed" "breed" "bread"]
  ;;          (doublets "wheat" "bread"))))

  ;; (testing "with no word links found"
  ;;   (is (= []
  ;;          (doublets "ye" "freezer"))))

  (testing "checks words are the same length and that they're both strings"
    (is (true? (words-same-length? "cat" "dog")))
    (is (false? (words-same-length? "cat" "bloop")))
    (is (thrown? AssertionError (words-same-length? 5 [:a :b :c]))))

  (testing "checks word is present in dictionary"
    (is (true? (word-in-array? "concentricity" words))))

  (testing "checks two words of equal length are one letter different"
    (is (true? (words-one-letter-different? "hello" "hullo")))
    (is (false? (words-one-letter-different? "hello" "hurly")))
    ;; (is (thrown? AssertionError (words-one-letter-different? "hell" "hello")))
    )

  (testing (str "gets words that are one letter different from each other "
                "from dictionary")
    (is (= ["heal"] (get-one-letter-different-words "head" words)))
    (is (= ["head" "teal"] (get-one-letter-different-words "heal" words))))

  (testing "with word links found"
    (is (= ["head" "heal" "teal" "tell" "tall" "tail"]
           (doublets "head" "tail")))
    (is (= ["door" "boor" "book" "look" "lock"]
           (doublets "door" "lock")))))

(deftest util
  (testing "gets single index of where vectors differ"
    (is (= [1] (get-in-order-vector-difference "hello" "hullo"))))
  (testing "gets indexes of where vectors differ"
    (is (= [1 2 4] (get-in-order-vector-difference "hello" "hurly")))))
