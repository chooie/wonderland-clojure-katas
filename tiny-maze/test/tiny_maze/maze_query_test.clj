(ns tiny-maze.maze-query-test
  (:require  [clojure.test :refer :all]
             [tiny-maze.maze-query :refer :all]))

(def correct-maze [[:S 0  1]
                   [1  0  1]
                   [1  0 :E]])

(def wrong-maze [[1 1 1]
                 [1 1 1]
                 [1 1 1]])

(deftest querying-maze
  (testing "getting position of element from maze"
    (is (= {:row-index 0 :column-index 0}
           (get-position-of-elem-from-maze correct-maze :S)))
    (is (= {:row-index -1 :column-index -1}
           (get-position-of-elem-from-maze wrong-maze :S))))
  (testing "getting positions around positions"
    (testing "top left corner"
      (is (= #{{:row-index 0 :column-index 1}
              {:row-index 1 :column-index 0}}
             (get-positions-around-position correct-maze
                                            {:row-index 0 :column-index 0}))))
    (testing "top right corner"
      (is (= #{{:row-index 0 :column-index 1}
               {:row-index 1 :column-index 2}}
             (get-positions-around-position correct-maze
                                            {:row-index 0 :column-index 2}))))
    (testing "bottom left corner"
      (is (= #{{:row-index 1 :column-index 0}
               {:row-index 2 :column-index 1}}
             (get-positions-around-position correct-maze
                                            {:row-index 2 :column-index 0}))))
    (testing "bottom right corner"
      (is (= #{{:row-index 1 :column-index 2}
               {:row-index 2 :column-index 1}}
             (get-positions-around-position correct-maze
                                            {:row-index 2 :column-index 2}))))
    (testing "position not in maze"
      (is (thrown? Exception
                   (get-positions-around-position correct-maze
                                                  {:row-index 999
                                                   :column-index 999}))))))
