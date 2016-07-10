(ns tiny-maze.maze-query.maze-query
  (:require [tiny-maze.maze-structure :as m-s]
            [tiny-maze.maze-query.position :as p]
            [tiny-maze.maze-query.surrounding-positions :as s-p]))

(defn get-start-position
  [maze]
  {:pre [(m-s/is-square-maze? maze)]}
  (p/get-position-of-elem-from-maze maze :S))

(defn get-end-position
  [maze]
  {:pre [(m-s/is-square-maze? maze)]}
  (p/get-position-of-elem-from-maze maze :E))

(defn get-symbol-at-position
  [maze {:keys [row-index column-index] :as position}]
  {:pre [(m-s/is-square-maze? maze)]}
  (get-in maze [row-index column-index]))

(defn get-position-and-symbol-at-position
  [maze position]
  (let [symbol (get-symbol-at-position maze position)
        position-and-symbol {:position position :symbol symbol}]
    position-and-symbol))

#_(defn get-surrounding-positions-with-symbols-of-position
  [maze position]
  {:pre [(m-s/is-square-maze? maze)]}
  (let [positions (s-p/get-positions-around-position maze position)]
    (mapv (fn [position] (foo maze position)) positions)))
