(ns magic-square.puzzle-test
  (:require [clojure.test :refer :all]
            [magic-square.puzzle :refer :all]))

(defn sum-rows [m]
  (map #(reduce + %) m))

(defn sum-cols [m]
  [(reduce + (map first m))
   (reduce + (map second m))
   (reduce + (map last m))])

(defn sum-diagonals [m]
  [(+ (get-in m [0 0]) (get-in m [1 1]) (get-in m [2 2]))
   (+ (get-in m [2 0]) (get-in m [1 1]) (get-in m [0 2]))])

(deftest test-magic-square
  (testing "it generates a 3 by 3 vector from a list of 9 elems"
    (let [test-vec (generate-3-by-3-vector values)]
      (is (is-3-by-3-vector? test-vec))))
  (testing "permutations"
    (testing "it generates all permutations of an array"
      (is (= #{[1 2 3] [1 3 2] [2 1 3] [2 3 1] [3 1 2] [3 2 1]}
             (permute-array [1 2 3]))))
    (testing "it generates all permutations of 3 by 3 vector"
      (is (permute-3-by-3-vector [])))))

;; (testing "all the rows, columns, and diagonal add to the same number"
;;     (is (= (set (sum-rows (magic-square values)))
;;            (set (sum-cols (magic-square values)))
;;            (set (sum-diagonals (magic-square values)))))

;;     (is (= 1
;;            (count (set (sum-rows (magic-square values))))
;;            (count (set (sum-cols (magic-square values))))
;;            (count (set (sum-diagonals (magic-square values)))))))
