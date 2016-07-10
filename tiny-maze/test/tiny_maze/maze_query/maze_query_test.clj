(ns tiny-maze.maze-query.maze-query-test
  (:require  [clojure.test :refer :all]
             [tiny-maze.maze-query.maze-query :refer :all]
             [tiny-maze.test-mazes :refer :all]))

(deftest querying-maze
  (testing "getting position of element from maze"
    (is (= {:row-index 0 :column-index 0}
           (get-position-of-elem-from-maze correct-maze :S)))
    (is (= {:row-index 2 :column-index 2}
           (get-position-of-elem-from-maze correct-maze :E)))
    (is (= {:row-index -1 :column-index -1}
           (get-position-of-elem-from-maze wrong-maze :S)))))
