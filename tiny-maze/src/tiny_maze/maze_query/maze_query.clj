(ns tiny-maze.maze-query.maze-query
  (:require [tiny-maze.maze-structure :as m-s]
            [tiny-maze.maze-query.position :as p]
            [tiny-maze.maze-query.surrounding-positions :as s-p]
            [tiny-maze.constants :as c]))

(def start-symbol (:start-position c/symbols))
(def end-symbol (:end-position c/symbols))
(def empty-symbol (:empty-space c/symbols))
(def wall-symbol (:blocking-wall c/symbols))
(def current-symbol (:current-position c/symbols))

(defn get-start-position
  [maze]
  {:pre [(m-s/is-square-maze? maze)]}
  (p/get-position-of-elem-from-maze maze start-symbol))

(defn get-end-position
  [maze]
  {:pre [(m-s/is-square-maze? maze)]}
  (p/get-position-of-elem-from-maze maze end-symbol))

(defn get-current-position
  [maze]
  {:pre [(m-s/is-square-maze? maze)]}
  (p/get-position-of-elem-from-maze maze current-symbol))

(defn get-symbol-at-position
  [maze {:keys [row-index column-index] :as position}]
  {:pre [(m-s/is-square-maze? maze)]}
  (get-in maze [row-index column-index]))

(defn get-position-and-symbol-at-position
  [maze position]
  (let [symbol (get-symbol-at-position maze position)
        position-and-symbol {:position position :symbol symbol}]
    position-and-symbol))

(defn get-positions-with-symbols-for-positions
  [maze positions]
  (mapv (fn [position] (get-position-and-symbol-at-position maze
                                                            position))
          positions))

(defn get-surrounding-positions-with-symbols-of-position
  [maze position]
  {:pre [(m-s/is-square-maze? maze)]}
  (let [positions (s-p/get-positions-around-position maze position)
        positions-with-symbols (get-positions-with-symbols-for-positions
                                maze positions)]
    (set positions-with-symbols)))
