(ns tiny-maze.maze-query.surrounding-positions
  (:require [tiny-maze.maze-query.position :as p]))

(defn get-all-surrounding-positions
  [maze {:keys [row-index column-index] :as position}]
  (let [row-above       (dec row-index)
        row-below       (inc row-index)
        column-to-left  (dec column-index)
        column-to-right (inc column-index)]
    #{{:row-index row-above :column-index column-index}
      {:row-index row-below :column-index column-index}
      {:row-index row-index :column-index column-to-left}
      {:row-index row-index :column-index column-to-right}}))

(defn get-positions-around-position
  [maze position]
  {:pre [(p/is-inside-bounds-of-maze? maze position)]}
  (let [positions (get-all-surrounding-positions maze position)
        valid-positions (filterv (fn [position]
                                   (p/is-inside-bounds-of-maze? maze position))
                                 positions)
        valid-positions-set (set valid-positions)]
    valid-positions-set))
