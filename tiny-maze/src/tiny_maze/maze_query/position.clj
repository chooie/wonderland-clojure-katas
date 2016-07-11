(ns tiny-maze.maze-query.position
  (:require [tiny-maze.maze-structure :as m-s]))

(defn get-index-of-elem
  [arr elem]
  (if arr
    (.indexOf arr elem)
    -1))

(defn row-contains-elem?
  [row elem]
  (boolean (some #{elem} row)))

(defn- get-rows-from-maze-that-contain-elem
  [elem maze]
  (filterv (fn [row]
             (row-contains-elem? row elem))
           maze))

(defn get-index-of-row-containing-elem
  "N.b. For now just gets the first one"
  [maze elem]
  (let [matching-row (first (get-rows-from-maze-that-contain-elem elem maze))]
    (get-index-of-elem maze matching-row)))

(defn get-position-of-elem-from-maze
  "N.b. assumes only one element"
  [maze elem]
  (let [occurred-row-index    (get-index-of-row-containing-elem maze elem)
        occurred-row          (get maze occurred-row-index)
        occurred-column-index (get-index-of-elem occurred-row elem)]
    {:row-index occurred-row-index :column-index occurred-column-index}))

(defn is-first-row?
  [maze row-index]
  (= 0 row-index))

(defn is-last-row?
  [maze row-index]
  (let [number-of-rows (m-s/get-number-of-rows maze)]
    (= number-of-rows (inc row-index))))

(defn is-first-column?
  [maze column-index]
  (= 0 column-index))

(defn is-last-column?
  [maze column-index]
  (let [number-of-columns (m-s/get-number-of-columns-for-maze maze)]
    (= number-of-columns (inc column-index))))

(defn is-inside-bounds-of-maze?
  [maze {:keys [row-index column-index] :as position}]
  (let [number-of-rows    (m-s/get-number-of-rows maze)
        number-of-columns (m-s/get-number-of-columns-for-maze maze)]
    (and (< -1 row-index)
         (< row-index number-of-rows)
         (< -1 column-index)
         (< column-index number-of-columns))))
