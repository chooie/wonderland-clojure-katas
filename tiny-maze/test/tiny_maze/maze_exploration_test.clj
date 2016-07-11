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
           (move-to-start-position correct-maze))))
  (testing "Checks maze has been started"
    (is (false? (maze-has-been-started? correct-maze)))
    (is (true? (maze-has-been-started? next-maze))))
  (testing "Checks maze has been finished"
    (is (false? (maze-has-been-finished? two-from-solved-maze)))
    (is (true? (maze-has-been-finished? one-from-solved-maze))))
  (testing "Change a symbol in maze"
    (is (= next-maze
           (change-symbol-in-maze correct-maze
                                  {:position {:row-index 0 :column-index 0}
                                   :symbol   current-symbol}))))
  (testing "Update maze symbols"
    (is (= next-next-maze
           (update-maze-symbols next-maze
                                {:previous-position
                                 {:position {:row-index 0 :column-index 0}
                                  :symbol   travelled-symbol}
                                 :current-position
                                 {:position {:row-index 0 :column-index 1}
                                  :symbol   current-symbol}}))))
  (testing "Advance maze"
    (is (= #{next-next-maze}
           (advance-maze next-maze)))
    (is (= #{one-from-solved-maze}
           (advance-maze two-from-solved-maze)))
    (is (= #{solved-maze}
           (advance-maze one-from-solved-maze)))))

