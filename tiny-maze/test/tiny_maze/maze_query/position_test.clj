(ns tiny-maze.maze-query.position-test
  (:require [clojure.test :refer :all]
            [tiny-maze.maze-query.position :refer :all]
            [tiny-maze.test-mazes :refer :all]
            [tiny-maze.constants :as c]))

(deftest position
  (testing "Getting position of element from maze"
    (is (= {:row-index 0 :column-index 0}
           (get-position-of-elem-from-maze correct-maze
                                           (:start-position c/symbols))))
    (is (= {:row-index 2 :column-index 2}
           (get-position-of-elem-from-maze correct-maze
                                           (:end-position c/symbols))))
    (is (= {:row-index -1 :column-index -1}
           (get-position-of-elem-from-maze wrong-maze
                                           (:start-position c/symbols)))))
  (testing "Checks positions are within bounds of maze"
    (is (true? (is-inside-bounds-of-maze? correct-maze
                                          {:row-index 0 :column-index 0})))
    (is (false? (is-inside-bounds-of-maze? correct-maze
                                           {:row-index 999
                                            :column-index 999})))))
