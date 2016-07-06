(ns magic-square.puzzle)

(def values [1.0 1.5 2.0 2.5 3.0 3.5 4.0 4.5 5.0])

(defn magic-square [values]
  [[1.0 1.5 2.0]
   [2.5 3.0 3.5]
   [4.0 4.5 5.0]])

(defn generate-3-by-3-vector
  [[a b c d e f g h i :as values]]
  {:pre [(= 9 (count values))]}
  [[a b c]
   [d e f]
   [g h i]])

(defn has-3-rows?
  [test-vector]
  (= 3 (count test-vector)))

(defn has-3-columns?
  [test-vector]
  (let [row-1 (get test-vector 0)
        row-2 (get test-vector 1)
        row-3 (get test-vector 2)]
    (and (= 3 (count row-1))
         (= 3 (count row-2))
         (= 3 (count row-3)))))

(defn is-3-by-3-vector?
  [test-vector]
  (and (has-3-rows? test-vector)
       (has-3-columns? test-vector)))

(defn array-size-2?
  [elems]
  (= 2 (count elems)))

(defn get-array-less-elem
  [elem arr]
  (filterv #(not= elem %) arr))

(defn permute-array
  [elems]
  (if (array-size-2? elems)
    (let [[a b] elems]
      [[a b]
       [b a]])
    (into #{} (map vec (partition (count elems)
                                 (vec (flatten (mapv (fn [elem]
                                                       (let [array-less-elem (get-array-less-elem elem elems)]
                                                         (let [remaining-elems-permutations (permute-array array-less-elem)]
                                                           (mapv (fn [permutation]
                                                                   (vec (concat [elem] permutation)))
                                                                 remaining-elems-permutations))))
                                                     elems))))))))

(defn permute-3-by-3-vector
  [test-vector]
  [])
