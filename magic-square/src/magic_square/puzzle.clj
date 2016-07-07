(ns magic-square.puzzle
  (:require [magic-square.permute :as p]
            [magic-square.vector-3-by-3 :as vec-3-3]))

(def values [1.0 1.5 2.0 2.5 3.0 3.5 4.0 4.5 5.0])

(declare magic-square
         is-magic-square?)

(defn magic-square
  [values]
  {:pre [(= 9 (count values))]}
  (let [square-permutations (p/generate-3-by-3-permutations values)
        solutions           (filter is-magic-square? square-permutations)]
    (first solutions)))

(defn is-magic-square?
  [square]
  (let [sum-rows-set      (set (vec-3-3/sum-rows square))
        sum-cols-set      (set (vec-3-3/sum-cols square))
        sum-diagonals-set (set (vec-3-3/sum-diagonals square))]
    (and (= sum-rows-set
            sum-cols-set
            sum-diagonals-set)
         (= 1
            (count sum-rows-set)
            (count sum-cols-set)
            (count sum-diagonals-set)))))
