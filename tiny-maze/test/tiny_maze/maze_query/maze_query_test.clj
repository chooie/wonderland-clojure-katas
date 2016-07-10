(ns tiny-maze.maze-query.maze-query-test
  (:require  [clojure.test :refer :all]
             [tiny-maze.maze-query.maze-query :refer :all]
             [tiny-maze.test-mazes :refer :all]
             [tiny-maze.constants :as c]))

(deftest querying-maze
  (testing "Getting position of element from maze"
    (is (= {:row-index 0 :column-index 0}
           (get-position-of-elem-from-maze correct-maze :S)))
    (is (= {:row-index 2 :column-index 2}
           (get-position-of-elem-from-maze correct-maze :E)))
    (is (= {:row-index -1 :column-index -1}
           (get-position-of-elem-from-maze wrong-maze :S))))
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
