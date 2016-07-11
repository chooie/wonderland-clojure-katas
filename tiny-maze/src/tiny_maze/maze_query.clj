(ns tiny-maze.maze-query
  (:require [tiny-maze.maze-structure :as m-s]))

(defn get-index-of-elem
  [arr elem]
  (if arr
    (.indexOf arr elem)
    -1))

(defn row-contains-elem?
  [row elem]
  (boolean (some #{elem} row)))

(defn get-index-of-row-containing-elem
  "N.b. For now just gets the first one"
  [maze elem]
  (let [matching-row (first (filterv (fn [row]
                                       (row-contains-elem? row elem))
                                     maze))]
    (get-index-of-elem maze matching-row)))

(defn get-position-of-elem-from-maze
  "N.b. assumes only one start-position"
  [maze elem]
  (let [occurred-row-index    (get-index-of-row-containing-elem maze elem)
        occurred-row          (get maze occurred-row-index)
        occurred-column-index (get-index-of-elem occurred-row elem)]
    {:row-index occurred-row-index :column-index occurred-column-index}))

(defn get-start-position
  [maze]
  {:pre [(m-s/is-square-maze? maze)]}
  (get-position-of-elem-from-maze maze :S))

(defn get-end-position
  [maze]
  {:pre [(m-s/is-square-maze? maze)]}
  (get-position-of-elem-from-maze maze :E))