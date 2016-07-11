(ns tiny-maze.maze-exploration
  (:require [tiny-maze.maze-query.maze-query :as m-q]
            [tiny-maze.constants :as c]))

(def space-symbol     (:empty-space      c/symbols))
(def travelled-symbol (:travelled-space  c/symbols))
(def current-symbol   (:current-position c/symbols))

(defn is-available-position?
  [{:keys [symbol] :as position-with-symbol}]
  (= space-symbol symbol))

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
  [{:keys [position] :as current-step} {:keys [position] :as next-step}]
  {:pre [(is-current-position? current-step)
         (is-available-position? next-step)]}
  (let [travelled-step (assoc current-step :symbol travelled-symbol)
        next-step      (assoc next-step :symbol current-symbol)]
    {:previous-position travelled-step
     :current-position  next-step}))

(defn generate-next-maze
  [maze]
  )

(defn advance-maze
  [maze])
