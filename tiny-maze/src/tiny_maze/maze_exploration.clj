(ns tiny-maze.maze-exploration
  (:require [tiny-maze.maze-query.maze-query :as m-q]
            [tiny-maze.constants :as c]
            [tiny-maze.maze-structure :as m-s]
            [tiny-maze.maze-query.position :as p]))

(def space-symbol     (:empty-space      c/symbols))
(def travelled-symbol (:travelled-space  c/symbols))
(def current-symbol   (:current-position c/symbols))
(def end-symbol       (:end-position     c/symbols))

(defn is-available-position?
  [{:keys [symbol] :as position-with-symbol}]
  (or (= space-symbol symbol)
      (= end-symbol symbol)))

(defn is-current-position?
  [{:keys [symbol :as position-with-symbol]}]
  (= current-symbol symbol))

(defn get-next-possible-steps
  [maze {:keys [row-index column-index] :as position}]
  (let [surrounding-positions-with-symbols
        (m-q/get-surrounding-positions-with-symbols-of-position
         maze position)
        possible-next-steps     (filterv is-available-position?
                                         surrounding-positions-with-symbols)
        possible-next-steps-set (set possible-next-steps)]
    possible-next-steps-set))

(defn take-next-step
  [current-step next-step]
  {:pre [(is-current-position? current-step)
         (is-available-position? next-step)]}
  (let [travelled-step (assoc current-step :symbol travelled-symbol)
        next-step      (assoc next-step :symbol current-symbol)]
    {:previous-position travelled-step
     :current-position  next-step}))

(defn maze-has-been-started?
  [maze]
  (let [start-position (m-q/get-start-position maze)
        current-position (m-q/get-current-position maze)]
    (and ((complement p/is-inside-bounds-of-maze?) maze start-position)
         (p/is-inside-bounds-of-maze? maze current-position))))

(defn maze-has-been-finished?
  [maze]
  (let [end-position     (m-q/get-end-position maze)
        current-position (m-q/get-current-position maze)]
    (and ((complement p/is-inside-bounds-of-maze?) maze end-position)
         (p/is-inside-bounds-of-maze? maze current-position))))

#_(defn generate-next-maze
  [maze]
 )

(defn change-symbol-in-maze
  [maze position-with-symbol]
  (let [{:keys [position symbol]} position-with-symbol
        {:keys [row-index column-index]} position]
    (assoc-in maze [row-index column-index] symbol)))

(defn update-maze-symbols
  [maze {:keys [previous-position current-position]}]
  (let [maze-with-updated-prev-pos             (change-symbol-in-maze
                                                maze previous-position)
        maze-with-updated-prev-and-current-pos (change-symbol-in-maze
                                                maze-with-updated-prev-pos
                                                current-position)]
    maze-with-updated-prev-and-current-pos))

(defn advance-maze
  [maze]
  {:pre [(maze-has-been-started? maze)]}
  (let [current-position             (m-q/get-current-position maze)
        current-position-with-symbol (m-q/get-position-and-symbol-at-position
                                      maze current-position)]
    (if (maze-has-been-finished? maze)
      (let [end-position-with-symbol (assoc current-position-with-symbol
                                            :symbol travelled-symbol)
            finished-maze            (change-symbol-in-maze
                                      maze
                                      end-position-with-symbol)]
        (set [finished-maze]))
      (let [next-steps  (get-next-possible-steps maze current-position)
            taken-steps (mapv (fn [next-step]
                                (take-next-step current-position-with-symbol
                                                next-step))
                              next-steps)
            new-mazes   (mapv (fn [taken-step]
                                (update-maze-symbols maze taken-step))
                              taken-steps)]
        (set new-mazes)))))

(defn move-to-start-position
  [maze]
  (let [start-position (m-q/get-start-position maze)
        {:keys [row-index column-index]} start-position
        next-maze (assoc-in maze [row-index column-index] current-symbol)]
    next-maze))
