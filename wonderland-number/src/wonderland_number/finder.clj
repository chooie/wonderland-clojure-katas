(ns wonderland-number.finder)

(def first-six-digit-num 100000)

(def first-seven-digit-num 1000000)

(defn sameDigits?
  [n1 n2]
  (let [s1 (set (str n1))
        s2 (set (str n2))]
    (= s1 s2)))

(defn wonderland-number
  []
  (let [possible-numbers (range first-six-digit-num first-seven-digit-num)]
    (first (filter (fn [n]
                     (and (sameDigits? n (* 2 n))
                          (sameDigits? n (* 3 n))
                          (sameDigits? n (* 4 n))
                          (sameDigits? n (* 5 n))
                          (sameDigits? n (* 6 n))))
                   possible-numbers))))
