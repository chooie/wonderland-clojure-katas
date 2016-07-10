(ns tiny-maze.solver
  (:require [tiny-maze.maze-structure :as m-s]
            [tiny-maze.maze-query.maze-query :as m-q]))

(declare solve-maze)

(defn get-available-positions-around-position
  [maze current-position])

(defn solve-maze
  [maze]
  {:pre [(m-s/is-square-maze? maze)]}
  (let [start-position (m-q/get-start-position maze)
        end-position (m-q/get-end-position maze)]
    end-position))
