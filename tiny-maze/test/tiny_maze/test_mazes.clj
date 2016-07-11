(ns tiny-maze.test-mazes
  (:require [tiny-maze.constants :as c]))

(def s (:start-position   c/symbols))
(def e (:end-position     c/symbols))
(def w (:blocking-wall    c/symbols))
(def _ (:empty-space      c/symbols))
(def t (:travelled-space  c/symbols))
(def c (:current-position c/symbols))

(def correct-maze [[s _ w]
                   [w _ w]
                   [w _ e]])

(def start-maze correct-maze)

(def next-maze [[c _ w]
                [w _ w]
                [w _ e]])

(def wrong-maze [[w w w]
                 [w w w]
                 [w w w]])
