(ns alphabet-cipher.coder)

(def alphabet
  "abcdefghijklmnopqrstuvwxyz")

(defn rotate-str-left
 [n coll]
  (let [n         (rem n (count coll))
        end       (vec (take n coll))
        beginning (vec (drop n coll))]
    (apply str (apply conj beginning end))))

(defn generate-substitution-chart-row
  [n coll]
  (rotate-str-left n coll))

(defn generate-substitution-chart
  []
  (map (fn [index] (generate-substitution-chart-row index alphabet))
       (take (count alphabet) (range))))

(defn extend-characters-by-n
  [characters n]
  (apply str (take n (apply concat [] (repeat characters)))))

(defn string-to-indices
  [string]
  (map (fn [c]
         (- (int c) (int \a)))
       string))

(defn get-char-from-substitution-chart
  [chart column-index row-index]
  (let [row (nth chart row-index)]
    (nth row column-index)))

(defn encode-from-chart
  [chart keyword-indices message-indices]
  (apply str (map (fn [keyword-index message-index]
                      (get-char-from-substitution-chart chart
                                                        keyword-index
                                                        message-index))
                    keyword-indices
                    message-indices)))

(defn encode [keyword message]
  (let [substitution-chart (generate-substitution-chart)
        extended-keyword (extend-characters-by-n keyword (count message))
        keyword-indices (string-to-indices extended-keyword)
        message-indices (string-to-indices message)]
    (encode-from-chart substitution-chart keyword-indices message-indices)))

(defn decode [keyword message]
  (let [substitution-chart (generate-substitution-chart)
        extended-keyword (extend-characters-by-n (count message))
        keyword-indices (string-to-indices extended-keyword)]))

(defn decipher [cipher message]
  "decypherme")

