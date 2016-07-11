(ns tiny-maze.test-mazes
  (:require [tiny-maze.constants :as c]))

(def s (:start-position   c/symbols))
(def e (:end-position     c/symbols))
(def w (:blocking-wall    c/symbols))
(def _ (:empty-space      c/symbols))
(def t (:travelled-space  c/symbols))
(def c (:current-position c/symbols))

(def correct-maze
  [[s _ w]
   [w _ w]
   [w _ e]])

(def start-maze correct-maze)

(def next-maze
  [[c _ w]
   [w _ w]
   [w _ e]])

(def next-next-maze
  [[t c w]
   [w _ w]
   [w _ e]])

(def two-from-solved-maze
  [[t t w]
   [w t w]
   [w c e]])

(def one-from-solved-maze
  [[t t w]
   [w t w]
   [w t c]])

(def solved-maze
  [[t t w]
   [w t w]
   [w t t]])

(def blocked-maze
  [[t t w]
   [t t e]
   [t c w]])

(def multi-option-maze
  [[t t w]
   [_ c _]
   [w w e]])

(def next-step-maze-1
  [[t t w]
   [c t _]
   [w w e]])

(def next-step-maze-2
  [[t t w]
   [_ t c]
   [w w e]])

(def wrong-maze
  [[w w w]
   [w w w]
   [w w w]])

(def start-maze-4-by-4
  [[s _ _ w]
   [w w _ _]
   [w _ _ w]
   [w w _ e]])

(def solved-maze-4-by-4
  [[t t t w]
   [w w t _]
   [w _ t w]
   [w w t t]])

(def start-maze-looped
  [[s _ _ _ _]
   [w _ w w _]
   [w _ w w _]
   [w _ _ _ _]
   [w e w w w]])

(def solved-maze-lopped
  [[t t _ _ _]
   [w t w w _]
   [w t w w _]
   [w t _ _ _]
   [w t w w w]])
