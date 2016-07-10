(ns tiny-maze.maze-query.maze-query-test
  (:require  [clojure.test :refer :all]
             [tiny-maze.maze-query.maze-query :refer :all]
             [tiny-maze.test-mazes :refer :all]
             [tiny-maze.constants :as c]))

(def start-symbol (:start-position c/symbols))
(def end-symbol (:end-position c/symbols))
(def empty-symbol (:empty-space c/symbols))
(def wall-symbol (:blocking-wall c/symbols))

(deftest querying-maze
  (testing "Getting symbol at position"
    (is (= start-symbol
           (get-symbol-at-position correct-maze
                                   {:row-index 0 :column-index 0})))
    (is (= end-symbol
           (get-symbol-at-position correct-maze
                                   {:row-index 2 :column-index 2})))
    (is (= empty-symbol
           (get-symbol-at-position correct-maze
                                   {:row-index 1 :column-index 1}))))
  (testing "Getting symbol and position at position"
    (is (= {:position {:row-index 0 :column-index 0}
            :symbol start-symbol}
           (get-position-and-symbol-at-position correct-maze
                                                {:row-index 0
                                                 :column-index 0}))))
  (testing "Getting surrounding positions-with-symbols of position"
    (is (= #{{:position {:row-index 0 :column-index 1}
              :symbol empty-symbol}
             {:position {:row-index 1 :column-index 0}
              :symbol wall-symbol}
             {:position {:row-index 1 :column-index 2}
              :symbol wall-symbol}
             {:position {:row-index 2 :column-index 1}
              :symbol empty-symbol}}
           (get-surrounding-positions-with-symbols-of-position
            correct-maze
            {:row-index 1
             :column-index 1})))))
