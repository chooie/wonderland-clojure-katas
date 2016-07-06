(ns magic-square.util)

(defn get-array-less-elem
  [elem arr]
  (filterv #(not= elem %) arr))

(defn flatten-once
  [arr]
  (apply concat arr))

(defn array-size-2?
  [elems]
  (= 2 (count elems)))

