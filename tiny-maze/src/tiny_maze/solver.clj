(ns tiny-maze.solver
  (:require [tiny-maze.maze-structure :as m-s]
            [tiny-maze.maze-query.maze-query :as m-q]
            [tiny-maze.maze-query.surrounding-positions :as s-p]))

(declare solve-maze)

(defn solve-maze
  [maze]
  {:pre [(m-s/is-square-maze? maze)]}
  (let [start-position (m-q/get-start-position maze)
        end-position (m-q/get-end-position maze)]
    ))
