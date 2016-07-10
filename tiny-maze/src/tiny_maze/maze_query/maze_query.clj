(ns tiny-maze.maze-query.maze-query
  (:require [tiny-maze.maze-structure :as m-s]
            [tiny-maze.maze-query.position :as p]))

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
