(ns tiny-maze.solver
  (:require [tiny-maze.maze-structure :as m-s]))

(declare solve-maze
         square-maze)

(defn solve-maze [maze])

(defn square-maze
  [maze]
  {:pre [(m-s/is-square-maze? maze)]})
