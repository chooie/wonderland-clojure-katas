(ns tiny-maze.maze-query.surrounding-positions-test
  (:require [clojure.test :refer :all]
            [tiny-maze.maze-query.surrounding-positions :refer :all]
            [tiny-maze.test-mazes :refer :all]))

(deftest surrounding-positions
  (testing "Checks positions are within bounds of maze"
    (is (true? (is-inside-bounds-of-maze? correct-maze
                                          {:row-index 0 :column-index 0})))
    (is (false? (is-inside-bounds-of-maze? correct-maze
                                           {:row-index 999
                                            :column-index 999}))))
  (testing "Getting positions around positions"
    (testing "Top left corner"
      (is (= #{{:row-index 0 :column-index 1}
               {:row-index 1 :column-index 0}}
             (get-positions-around-position correct-maze
                                            {:row-index 0 :column-index 0}))))
    (testing "Top right corner"
      (is (= #{{:row-index 0 :column-index 1}
               {:row-index 1 :column-index 2}}
             (get-positions-around-position correct-maze
                                            {:row-index 0 :column-index 2}))))
    (testing "Bottom left corner"
      (is (= #{{:row-index 1 :column-index 0}
               {:row-index 2 :column-index 1}}
             (get-positions-around-position correct-maze
                                            {:row-index 2 :column-index 0}))))
    (testing "Bottom right corner"
      (is (= #{{:row-index 1 :column-index 2}
               {:row-index 2 :column-index 1}}
             (get-positions-around-position correct-maze
                                            {:row-index 2 :column-index 2}))))
    (testing "Top row but not corners"
      (is (= #{{:row-index 0 :column-index 0}
               {:row-index 0 :column-index 2}
               {:row-index 1 :column-index 1}}
             (get-positions-around-position correct-maze
                                            {:row-index 0 :column-index 1}))))
    (testing "Bottom row but not corners"
      (is (= #{{:row-index 1 :column-index 1}
               {:row-index 2 :column-index 0}
               {:row-index 2 :column-index 2}}
             (get-positions-around-position correct-maze
                                            {:row-index 2 :column-index 1}))))
    (testing "Left column but not corners"
      (is (= #{{:row-index 0 :column-index 0}
               {:row-index 1 :column-index 1}
               {:row-index 2 :column-index 0}}
             (get-positions-around-position correct-maze
                                            {:row-index 1 :column-index 0}))))
    (testing "Right column but not corners"
      (is (= #{{:row-index 0 :column-index 2}
               {:row-index 1 :column-index 1}
               {:row-index 2 :column-index 2}}
             (get-positions-around-position correct-maze
                                            {:row-index 1 :column-index 2}))))
    (testing "Internal position"
      (is (= #{{:row-index 0 :column-index 1}
               {:row-index 2 :column-index 1}
               {:row-index 1 :column-index 0}
               {:row-index 1 :column-index 2}}
             (get-positions-around-position correct-maze
                                            {:row-index 1 :column-index 1}))))
    (testing "Position not in maze"
      (is (thrown? java.lang.AssertionError
                   (get-positions-around-position correct-maze
                                                  {:row-index    999
                                                   :column-index 999}))))))
