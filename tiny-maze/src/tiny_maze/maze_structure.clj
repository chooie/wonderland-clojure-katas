(ns tiny-maze.maze-structure)

(declare is-square-maze?
         get-number-of-rows
         get-number-of-columns-for-maze
         get-number-of-columns-for-row
         equal-number-of-columns?
         all-values-are-equal?)

(defn is-square-maze?
  [maze]
  (try
    (= (get-number-of-rows maze)
       (get-number-of-columns-for-maze maze))
    (catch java.lang.AssertionError e false)))

(defn get-number-of-rows
  [maze]
  (count maze))

(defn get-number-of-columns-for-maze
  [maze]
  {:pre [(equal-number-of-columns? maze)]}
  (let [row-1 (get maze 0)]
    (get-number-of-columns-for-row row-1)))

(defn get-number-of-columns-for-row
  [row]
  (count row))

(defn equal-number-of-columns?
  [maze]
  (let [columns-for-each-row (mapv get-number-of-columns-for-row maze)]
    (all-values-are-equal? columns-for-each-row)))

(defn all-values-are-equal?
  [arr]
  (= 1 (count (set arr))))
