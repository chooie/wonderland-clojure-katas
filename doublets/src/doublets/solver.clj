(ns doublets.solver
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(def words (-> "words.edn"
               (io/resource)
               (slurp)
               (read-string)))

(defn doublets [word1 word2]
  (let [target-word word2]
    (loop [cur-word word1
           sequence [cur-word]]
      (if (= cur-word target-word)
        sequence
        (let [words-one-different-to-current-word
              (get-one-letter-different-words cur-word words)]
          (println words-one-different-to-current-word)
          (if (empty? words-one-different-to-current-word)
            sequence ;; (throw (Exception. "Not doublets"))
            ;; TODO: handle multiple cases
            (let [words-not-in-sequence (first (filterv #((complement word-in-array?)
                                                          % sequence)
                                                        words-one-different-to-current-word))]
              (recur words-not-in-sequence (conj sequence words-not-in-sequence)))))))))

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
