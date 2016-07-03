(ns doublets.solver-test
  (:require [clojure.test :refer :all]
            [doublets.solver :refer :all]))

(deftest solver-test
  (testing "checks words are the same length and that they're both strings"
    (is (true? (words-same-length? "cat" "dog")))
    (is (false? (words-same-length? "cat" "bloop")))
    (is (thrown? AssertionError (words-same-length? 5 [:a :b :c]))))

  (testing "checks word is present in dictionary"
    (is (true? (word-in-array? "concentricity" words))))

  (testing "checks two words of equal length are one letter different"
    (is (true? (words-one-letter-different? "hello" "hullo")))
    (is (false? (words-one-letter-different? "hello" "hurly"))))

  (testing (str "gets words that are one letter different from each other "
                "from dictionary")
    (is (= ["heal"] (get-one-letter-different-words "head" words)))
    (is (= ["head" "teal"] (get-one-letter-different-words "heal" words))))

  (testing "correctly advance possible solutions"
    (is (= [["head" "heal"]] (advance-possible-solution-branch ["head"] words)))
    (is (= [["head" "heal" "teal"]]
           (advance-possible-solution-branch ["head" "heal"]  words)))
    (is (= [["head" "heal" "teal" "tell"]]
           (advance-possible-solution-branch ["head" "heal" "teal"]
                                             words)))
    (is (= [["head" "heal" "teal" "tell" "tall"]]
           (advance-possible-solution-branch ["head" "heal" "teal" "tell"]
                                             words)))
    (is (= [["head" "heal" "teal" "tell" "tall" "tail"]]
           (advance-possible-solution-branch ["head" "heal" "teal" "tell"
                                              "tall"]
                                             words)))
    (is (= nil (advance-possible-solution-branch ["this-word-has-no-successor"]
                                                 words))))
  
  (testing (str "handles word doublets where there is only one possibility"
                "at each step")
    (is (= [["head" "heal" "teal"] ["wheat" "cheat" "cheap"]]
           (advance-branches [["head" "heal"] ["wheat" "cheat"]] words))))
  (testing (str "handles word doublets where there may be multiple"
                "possibilities at a step")
    )

  (testing "with word links found"
    (is (= ["head" "heal" "teal" "tell" "tall" "tail"]
           (doublets "head" "tail")))
    (is (= ["door" "boor" "book" "look" "lock"]
           (doublets "door" "lock")))
    (is (= ["wheat" "cheat" "cheap" "cheep" "creep" "creed" "breed" "bread"]
           (doublets "wheat" "bread")))))

  (testing "with no word links found"
    (is (= []
           (doublets "ye" "freezer"))))

(deftest util
  (deftest get-in-order-vector-difference-test
    (testing "gets single index of where vectors differ"
      (is (= [1] (get-in-order-vector-difference "hello" "hullo"))))
    (testing "gets indexes of where vectors differ"
      (is (= [1 2 4] (get-in-order-vector-difference "hello" "hurly")))))
  (deftest flatten-once-test
    (testing "correctly flattens a sequence"
      (is (= [1 2 3] (flatten-once [[1] [2] [3]]))))))
