(ns tiny-maze.maze-structure)

(def start-symbol :S)
(def end-symbol :E)

(declare is-square-maze?
         get-number-of-rows
         get-number-of-columns-for-maze
         get-number-of-columns-for-row
         equal-number-of-columns?
         all-values-are-equal?)

(defn is-square-maze?
  [maze]
  (= (get-number-of-rows maze)
     (get-number-of-columns-for-maze maze)))

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
    all-values-are-equal? columns-for-each-row))

(defn all-values-are-equal?
  [arr]
  (= 1 (count (set arr))))

(defn get-index-of-elem
  [arr elem]
  (.indexOf arr elem))

(defn row-contains-elem?
  [row elem]
  (boolean (some #{elem} row)))

(defn get-index-of-row-containing-elem
  [maze elem]
  (let [matching-row (first (filterv (fn [row]
                                       (row-contains-elem? row elem))
                                     maze))]
    (get-index-of-elem maze matching-row)))

(defn get-start-position
  "N.b. assumes only one start-position"
  [maze]
  {:pre [(is-square-maze? maze)]}
  (let [occurred-row-index    (get-index-of-row-containing-elem maze start-symbol)
        occurred-row          (get maze occurred-row-index)
        occurred-column-index (get-index-of-elem occurred-row start-symbol)]
    {:row-index occurred-row-index :column-index occurred-column-index}))
