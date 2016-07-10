(ns tiny-maze.maze-query.maze-query-test
  (:require  [clojure.test :refer :all]
             [tiny-maze.maze-query.maze-query :refer :all]
             [tiny-maze.test-mazes :refer :all]
             [tiny-maze.constants :as c]))

(deftest querying-maze
  (testing "Getting symbol at position"
    (is (= (:start-position c/symbols)
           (get-symbol-at-position correct-maze
                                   {:row-index 0 :column-index 0})))
    (is (= (:end-position c/symbols)
           (get-symbol-at-position correct-maze
                                   {:row-index 2 :column-index 2})))
    (is (= (:empty-space c/symbols)
           (get-symbol-at-position correct-maze
                                   {:row-index 1 :column-index 1})))))
