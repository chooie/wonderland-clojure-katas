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

;; Positions and Surrounding positions ;;

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

(defn get-top-left-corner-positions
  [maze]
  #{{:row-index 0 :column-index 1}
    {:row-index 1 :column-index 0}})

(defn is-top-right-corner?
  [maze {:keys [row-index column-index] :as position}]
  (and (is-first-row? maze row-index)
       (is-last-column? maze column-index)))

(defn get-top-right-corner-positions
  [maze]
  (let [number-of-columns (m-s/get-number-of-columns-for-maze maze)
        last-column-index (dec number-of-columns)]
    #{{:row-index 0 :column-index (dec last-column-index)}
      {:row-index 1 :column-index last-column-index}}))

(defn is-bottom-left-corner?
  [maze {:keys [row-index column-index] :as position}]
  (and (is-last-row? maze row-index)
       (is-first-column? maze column-index)))

(defn get-bottom-left-corner-positions
  [maze]
  (let [number-of-rows (m-s/get-number-of-rows maze)
        last-row-index (dec number-of-rows)]
    #{{:row-index (dec last-row-index) :column-index 0}
      {:row-index last-row-index :column-index 1}}))

(defn is-bottom-right-corner?
  [maze {:keys [row-index column-index] :as position}]
  (and (is-last-row? maze row-index)
       (is-last-column? maze column-index)))

(defn get-bottom-right-corner-positions
  [maze]
  (let [number-of-rows    (m-s/get-number-of-rows maze)
        last-row-index    (dec number-of-rows)
        number-of-columns (m-s/get-number-of-columns-for-maze maze)
        last-column-index (dec number-of-columns)]
    #{{:row-index (dec last-row-index) :column-index last-column-index}
      {:row-index last-row-index :column-index (dec last-column-index)}}))

(defn get-positions-around-position
  [maze {:keys [row-index column-index] :as position}]
  (cond
    (is-top-left-corner? maze position)     (get-top-left-corner-positions maze)
    (is-top-right-corner? maze position)    (get-top-right-corner-positions
                                             maze)
    (is-bottom-left-corner? maze position)  (get-bottom-left-corner-positions
                                             maze)
    (is-bottom-right-corner? maze position) (get-bottom-right-corner-positions
                                             maze)
    :default                                (throw (Exception. "Not in maze"))))
