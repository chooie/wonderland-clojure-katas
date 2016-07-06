(ns magic-square.permute-test
  (:require [clojure.test :refer :all]
            [magic-square.permute :as p]))

(deftest permute-test
  (testing "it generates all permutations of an array"
    (is (= #{[1 2 3] [1 3 2] [2 1 3] [2 3 1] [3 1 2] [3 2 1]}
           (p/permute-array [1 2 3])))))
