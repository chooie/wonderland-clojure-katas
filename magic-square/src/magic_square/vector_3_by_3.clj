(ns magic-square.vector-3-by-3)

(declare generate-3-by-3-vector
         is-3-by-3-vector?
         has-3-rows?
         has-3-columns?
         sum-rows
         sum-cols
         sum-diagonals)

(defn generate-3-by-3-vector
  [[a b c d e f g h i :as values]]
  {:pre [(= 9 (count values))]}
  [[a b c]
   [d e f]
   [g h i]])

(defn is-3-by-3-vector?
  [test-vector]
  (and (has-3-rows? test-vector)
       (has-3-columns? test-vector)))

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

(defn sum-rows [m]
  (map #(reduce + %) m))

(defn sum-cols [m]
  [(reduce + (map first m))
   (reduce + (map second m))
   (reduce + (map last m))])

(defn sum-diagonals [m]
  [(+ (get-in m [0 0]) (get-in m [1 1]) (get-in m [2 2]))
   (+ (get-in m [2 0]) (get-in m [1 1]) (get-in m [0 2]))])
