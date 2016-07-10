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

;; Corners

(defn is-top-left-corner?
  [maze {:keys [row-index column-index] :as position}]
  (and (is-first-row? maze row-index)
       (is-first-column? maze column-index)))

(defn get-positions-around-top-left-corner
  [maze]
  #{{:row-index 0 :column-index 1}
    {:row-index 1 :column-index 0}})

(defn is-top-right-corner?
  [maze {:keys [row-index column-index] :as position}]
  (and (is-first-row? maze row-index)
       (is-last-column? maze column-index)))

(defn get-positions-around-top-right-corner
  [maze]
  (let [number-of-columns (m-s/get-number-of-columns-for-maze maze)
        last-column-index (dec number-of-columns)]
    #{{:row-index 0 :column-index (dec last-column-index)}
      {:row-index 1 :column-index last-column-index}}))

(defn is-bottom-left-corner?
  [maze {:keys [row-index column-index] :as position}]
  (and (is-last-row? maze row-index)
       (is-first-column? maze column-index)))

(defn get-positions-around-bottom-left-corner
  [maze]
  (let [number-of-rows (m-s/get-number-of-rows maze)
        last-row-index (dec number-of-rows)]
    #{{:row-index (dec last-row-index) :column-index 0}
      {:row-index last-row-index :column-index 1}}))

(defn is-bottom-right-corner?
  [maze {:keys [row-index column-index] :as position}]
  (and (is-last-row? maze row-index)
       (is-last-column? maze column-index)))

(defn get-positions-around-bottom-right-corner
  [maze]
  (let [number-of-rows    (m-s/get-number-of-rows maze)
        last-row-index    (dec number-of-rows)
        number-of-columns (m-s/get-number-of-columns-for-maze maze)
        last-column-index (dec number-of-columns)]
    #{{:row-index (dec last-row-index) :column-index last-column-index}
      {:row-index last-row-index :column-index (dec last-column-index)}}))

(defn is-top-row-and-not-corner?
  [maze {:keys [row-index column-index] :as position}]
  (let [number-of-columns (m-s/get-number-of-columns-for-maze maze)
        last-column-index (dec number-of-columns)]
    (and (= 0 row-index)
         (< 0 column-index)
         (< column-index last-column-index))))

(defn get-positions-around-top-row-position
  [maze {:keys [row-index column-index] :as position}]
  {:pre [is-top-row-and-not-corner? maze position]}
  (let [column-to-left  (dec column-index)
        column-to-right (inc column-index)
        row-below       (inc row-index)]
    #{{:row-index row-index :column-index column-to-left}
      {:row-index row-index :column-index column-to-right}
      {:row-index row-below :column-index column-index}}))

(defn is-bottom-row-and-not-corner?
  [maze {:keys [row-index column-index] :as position}]
  (let [number-of-rows    (m-s/get-number-of-rows maze)
        last-row-index    (dec number-of-rows)
        number-of-columns (m-s/get-number-of-rows maze)
        last-column-index (m-s/get-number-of-columns-for-maze maze)]
    (and (= row-index last-row-index)
         (< 0 column-index)
         (< column-index last-column-index))))

(defn get-positions-around-bottom-row-position
  [maze {:keys [row-index column-index] :as position}]
  {:pre [is-bottom-row-and-not-corner? maze position]}
  (let [row-above       (dec row-index)
        column-to-left  (dec column-index)
        column-to-right (inc column-index)]
    #{{:row-index row-index :column-index column-to-left}
      {:row-index row-index :column-index column-to-right}
      {:row-index row-above :column-index column-index}}))

(defn is-left-column-and-not-corner?
  [maze {:keys [row-index column-index] :as position}]
  (let [number-of-rows (m-s/get-number-of-rows maze)
        last-row-index (dec number-of-rows)]
    (and (< 0 row-index)
         (< row-index last-row-index)
         (= 0 column-index))))

(defn get-positions-around-left-column-position
  [maze {:keys [row-index column-index] :as position}]
  {:pre [is-left-column-and-not-corner? maze position]}
  (let [row-above       (dec row-index)
        row-below       (inc row-index)
        column-to-right (inc column-index)]
    #{{:row-index row-above :column-index column-index}
      {:row-index row-index :column-index column-to-right}
      {:row-index row-below :column-index column-index}}))

(defn is-right-column-and-not-corner?
  [maze {:keys [row-index column-index] :as position}]
  (let [number-of-rows    (m-s/get-number-of-rows maze)
        last-row-index    (dec number-of-rows)
        number-of-columns (m-s/get-number-of-columns-for-maze maze)
        last-column       (dec number-of-columns)]
    (and (< 0 row-index)
         (< row-index last-row-index)
         (= last-column column-index))))

(defn get-positions-around-right-column-position
  [maze {:keys [row-index column-index] :as position}]
  {:pre [is-right-column-and-not-corner? maze position]}
  (let [row-above      (dec row-index)
        row-below      (inc row-index)
        column-to-left (dec column-index)]
    #{{:row-index row-above :column-index column-index}
      {:row-index row-below :column-index column-index}
      {:row-index row-index :column-index column-to-left}}))

(defn is-internal-position?
  [maze {:keys [row-index column-index] :as position}]
  (let [number-of-rows    (m-s/get-number-of-rows maze)
        last-row-index    (dec number-of-rows)
        number-of-columns (m-s/get-number-of-columns-for-maze maze)
        last-column-index (dec number-of-columns)]
    (and (< 0 row-index)
         (< row-index last-row-index)
         (< 0 column-index)
         (< column-index last-column-index))))

(defn get-positions-around-internal-position
  [maze {:keys [row-index column-index] :as position}]
  {:pre [is-internal-position? maze position]}
  (let [row-above       (dec row-index)
        row-below       (inc row-index)
        column-to-left  (dec column-index)
        column-to-right (inc column-index)]
    #{{:row-index row-above :column-index column-index}
      {:row-index row-below :column-index column-index}
      {:row-index row-index :column-index column-to-left}
      {:row-index row-index :column-index column-to-right}}))

(defn get-positions-around-position
  [maze {:keys [row-index column-index] :as position}]
  (cond
    (is-top-left-corner?
     maze position) (get-positions-around-top-left-corner
                     maze)
    (is-top-right-corner?
     maze
     position)      (get-positions-around-top-right-corner
                     maze)
    (is-bottom-left-corner?
     maze
     position)      (get-positions-around-bottom-left-corner
                     maze)
    (is-bottom-right-corner?
     maze
     position)      (get-positions-around-bottom-right-corner
                     maze)
    (is-top-row-and-not-corner?
     maze
     position)      (get-positions-around-top-row-position
                     maze position)
    (is-bottom-row-and-not-corner?
     maze
     position)      (get-positions-around-bottom-row-position
                     maze position)
    (is-left-column-and-not-corner?
     maze
     position)      (get-positions-around-left-column-position
                     maze position)
    (is-right-column-and-not-corner?
     maze
     position)      (get-positions-around-right-column-position
                     maze position)
    (is-internal-position?
     maze
     position)      (get-positions-around-internal-position
                     maze position)
    :default        (throw (Exception. "Not in maze"))))
