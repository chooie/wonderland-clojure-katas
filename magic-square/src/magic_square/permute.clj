(ns magic-square.permute
  (:require [magic-square.util :as util]
            [magic-square.vector-3-by-3 :as vec-3-3]))

(declare permute-array
         permute-array-with-2-elems
         permute-array-with-more-than-2-elems
         make-permutations-with-elem
         combine-elem-and-permutation
         generate-3-by-3-permutations)

(defn permute-array
  [elems]
  (if (util/array-size-2? elems)
    (permute-array-with-2-elems elems)
    (permute-array-with-more-than-2-elems elems)))

(defn permute-array-with-2-elems
  [elems]
  (let [[a b] elems]
    [[a b]
     [b a]]))

(defn permute-array-with-more-than-2-elems
  [elems]
  (let [nested-permutations   (mapv #(make-permutations-with-elem %
                                                                  elems)
                                    elems)
        flattened-permutation (util/flatten-once nested-permutations)]
    (set flattened-permutation)))

(defn make-permutations-with-elem
  [elem elems]
  (let [array-less-elem (util/get-array-less-elem elem elems)]
    (let [remaining-elems-permutations (permute-array array-less-elem)]
      (mapv #(combine-elem-and-permutation elem %)
            remaining-elems-permutations))))

(defn combine-elem-and-permutation
  [elem permutation]
  (vec (concat [elem] permutation)))

(defn generate-3-by-3-permutations
  [values]
  {:pre [(= 9 (count values))]}
  (let [value-permutations   (permute-array values)
        elems-to-3-by-3-vecs (mapv vec-3-3/generate-3-by-3-vector
                                   value-permutations)]
    elems-to-3-by-3-vecs))
