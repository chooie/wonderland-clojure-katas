(ns tiny-maze.solver-test
  (:require [clojure.test :refer :all]
            [tiny-maze.solver :refer :all]
            [tiny-maze.test-mazes :refer :all]))

(deftest test-solve-maze
  (testing "Advance maze-tree"
    (is (= #{[start-maze next-maze next-next-maze]}
           (advance-maze-tree [start-maze next-maze])))
    (is (= #{[multi-option-maze next-step-maze-1]
             [multi-option-maze next-step-maze-2]}
           (advance-maze-tree [multi-option-maze])))
    (is (= #{}
           (advance-maze-tree [blocked-maze])))
    (is (= #{[one-from-solved-maze solved-maze]}
           (advance-maze-tree [one-from-solved-maze solved-maze]))))

  (testing "Advance maze-trees"
    (is (= #{[one-from-solved-maze solved-maze]}
           (advance-maze-trees #{[one-from-solved-maze]})))
    (is (= #{[multi-option-maze next-step-maze-1]
             [multi-option-maze next-step-maze-2]}
           (advance-maze-trees #{[multi-option-maze]})))
    (is (= #{}
           (advance-maze-trees #{[blocked-maze]})))
    (is (= #{[solved-maze]}
           (advance-maze-trees #{[solved-maze]}))))
  (testing "Can find way to exit with 3x3 maze"
    (is (= solved-maze
           (solve-maze start-maze))))
  (testing "Can find way to exit with 4x4 maze"
    (is (= solved-maze-4-by-4
           (solve-maze start-maze-4-by-4))))
  (testing "Can find way to exit with looped maze"
    (is (= solved-maze-lopped
           (solve-maze start-maze-looped)))))
