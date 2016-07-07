(ns magic-square.puzzle-test
  (:require [clojure.test :refer :all]
            [magic-square.puzzle :refer :all]))

(deftest test-magic-square
  (testing "all the rows, columns, and diagonals add to the same number"
    (is (is-magic-square? (magic-square values)))))
