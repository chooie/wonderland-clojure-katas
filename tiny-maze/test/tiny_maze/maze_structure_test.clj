(ns tiny-maze.maze-structure-test
  (:require [clojure.test :refer :all]
            [tiny-maze.maze-structure :refer :all]))
(deftest maze-structure
  (testing "columns managed correctly"
    (is true? (equal-number-of-columns? [[1 1 1]
                                         [1 1 1]
                                         [1 1 1]]))
    (is false? (equal-number-of-columns? [[1 1]
                                          [1 1 1]
                                          [1 1 1]])))
  (testing "square maze correctly identified"
    (is (is-square-maze? [[1 1 1]
                          [1 1 1]
                          [1 1 1]]))
    (is false? (is-square-maze? [[1 1 1]
                                 [1 1]
                                 [1 1 1]]))))
