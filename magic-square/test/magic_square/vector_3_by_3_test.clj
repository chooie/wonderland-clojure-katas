(ns magic-square.vector-3-by-3-test
  (:require  [clojure.test :refer :all]
             [magic-square.vector-3-by-3 :as vec-3-3]))

(deftest test-vector-3-by-3
  (testing "it generates a 3 by 3 vector from a list of 9 elems"
    (let [test-vec (vec-3-3/generate-3-by-3-vector [1 2 3 4 5 6 7 8 9])]
      (is (vec-3-3/is-3-by-3-vector? test-vec)))))
