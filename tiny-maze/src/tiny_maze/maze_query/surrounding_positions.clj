(ns tiny-maze.maze-query.surrounding-positions
  (:require [tiny-maze.maze-structure :as m-s]))

;; Rows and Columns

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

(defn is-inside-bounds-of-maze?
  [maze {:keys [row-index column-index] :as position}]
  (let [number-of-rows    (m-s/get-number-of-rows maze)
        number-of-columns (m-s/get-number-of-columns-for-maze maze)]
    (and (< -1 row-index)
         (< row-index number-of-rows)
         (< -1 column-index)
         (< column-index number-of-columns))))

(defn get-positions-around-position
  [maze position]
  {:pre [(is-inside-bounds-of-maze? maze position)]}
  (let [positions (get-all-surrounding-positions maze position)
        valid-positions (filterv (fn [position]
                                   (is-inside-bounds-of-maze? maze position))
                                 positions)
        valid-positions-set (set valid-positions)]
    valid-positions-set))
