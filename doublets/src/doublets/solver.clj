(ns doublets.solver
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(def words (-> "words.edn"
               (io/resource)
               (slurp)
               (read-string)))

(defn doublets
  [start-word target-word]
  (let [starting-solution-branch [start-word]]
    (loop [possible-solution-branches [starting-solution-branch]]
      (let [get-solution get-valid-solution-from-solution-branches
            solution (get-solution target-word possible-solution-branches)]
        (if solution
          solution
          (recur (advance-branches possible-solution-branches
                                   words)))))))

(defn flatten-once
  [nested-seq]
  (apply concat nested-seq))

(defn advance-branches
  [branches dictionary]
  (flatten-once
   (mapv (fn [branch]
           (advance-possible-solution-branch branch dictionary))
         branches)))

(defn advance-possible-solution-branch
  [solution-branch dictionary]
  (let [cur-word (last solution-branch)
        next-words (get-one-letter-different-words cur-word dictionary)
        not-in-array? (complement word-in-array?)
        word-not-occurred-before? (fn [word]
                                    (not-in-array? word solution-branch))
        next-words-not-occurred-before (filterv word-not-occurred-before?
                                                next-words)
        possible-solutions (mapv (fn [new-word]
                                   (conj solution-branch new-word))
                                 next-words-not-occurred-before)]
    (if (empty? possible-solutions)
      nil
      possible-solutions)))

(defn get-valid-solution-from-solution-branches
  [target-word possible-solution-branches]
  (first (filterv (partial valid-solution? target-word)
                  possible-solution-branches)))

(defn valid-solution?
  [target-word possible-solution]
  (= target-word (last possible-solution)))

(defn get-one-letter-different-words
  [word dictionary]
  (filterv #(words-one-letter-different? word %) dictionary))

(defn words-same-length?
  [word1 word2]
  {:pre [(string? word1) (string? word2)]}
  (= (count word1) (count word2)))

(defn word-in-array?
  [word arr]
  (boolean (some #{word} arr)))

(defn words-one-letter-different?
  [word1 word2]
  ;; {:pre [(words-same-length? word1 word2)]}
  (let [word1-vector (vec word1)
        word2-vector (vec word2)
        difference (get-in-order-vector-difference word1-vector word2-vector)]
    (= 1 (count difference))))

(defn get-in-order-vector-difference
  [vec1 vec2]
  (loop [differing-indexes []
         i 0]
    (if (= i (count vec1))
      differing-indexes
      (let [item1 (get vec1 i)
            item2 (get vec2 i)
            next-index (inc i)]
        (if (= item1 item2)
          (recur differing-indexes next-index)
          (recur (conj differing-indexes i) next-index))))))
