(ns tiny-maze.maze-exploration-test
  (:require [clojure.test :refer :all]
            [tiny-maze.maze-exploration :refer :all]
            [tiny-maze.test-mazes :refer :all]
            [tiny-maze.constants :as c]))

(deftest maze-exploration
  (testing "Get all possible next steps"
    (is (= #{{:position {:row-index 0 :column-index 1}
              :symbol   space-symbol}}
           (get-next-possible-steps correct-maze
                                    {:row-index 0 :column-index 0}))))
  (testing "Take next step"
    (is (= {:previous-position {:position {:row-index 0 :column-index 0}
                                :symbol   travelled-symbol}
            :current-position  {:position {:row-index 0 :column-index 1}
                                :symbol   current-symbol}}
           (take-next-step {:position {:row-index 0 :column-index 0}
                            :symbol   current-symbol}
                           {:position {:row-index 0 :column-index 1}
                            :symbol   space-symbol}))))
  (testing "Start maze traversal"
    (is (= next-maze
           (start-maze-traversal correct-maze))))
  (testing "Checks maze has been started"
    (is (false? (maze-has-been-started? correct-maze)))
    (is (true? (maze-has-been-started? next-maze)))))

