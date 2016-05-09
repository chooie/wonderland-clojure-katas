(ns fox-goose-bag-of-corn.puzzle)

(def start-pos [[[:fox :goose :corn :you] [:boat] []]])
(def end [[] [:boat] [:you :fox :goose :corn]])

(defn together?
  [bank elems]
  {:pre [(set? bank) (set? elems)]}
  (clojure.set/subset? elems bank))

(defn person-present?
  [place]
  {:pre [(set? place)]}
  (contains? place :you))

(defn valid-bank?
  [bank]
  {:pre [(set? bank)]}
  (let [not-together? (complement together?)]
    (or (person-present? bank)
        (and (not-together? bank #{:fox :goose})
             (not-together? bank #{:goose :corn})))))

(defn valid-boat?
  [boat]
  {:pre [(set? boat)]}
  (and (contains? boat :boat)
       (<= (count boat) 3)
       (condp = (count boat)
         1 true
         2 (contains? boat :you)
         3 (or (together? boat #{:you :goose})
               (together? boat #{:you :corn})
               (together? boat #{:you :fox})))))

(defn valid-state?
  [[left-bank boat right-bank]]
  (let [left-bank-set (set left-bank)
        boat-set (set boat)
        right-bank-set (set right-bank)]
    (and (valid-bank? left-bank-set)
         (valid-boat? boat-set)
         (valid-bank? right-bank-set))))

(defn one-different?
  [place-first place-next]
  {:pre [(set? place-first) (set? place-next)]}
  (let [diff1 (clojure.set/difference place-first place-next)
        diff2 (clojure.set/difference place-next place-first)
        diffs (concat diff1 diff2)
        diffs-count (count diffs)]
    (or (and (= diffs-count 2)
             (contains? (set diffs) :you))
        (and (= diffs-count 1)
             (contains? (set diffs) :you)))))

(defn move-away-from?
  [[place-1-first place-1-next] [place-2-first place-2-next]]
  {:pre [(set? place-1-first) (set? place-1-next)
         (set? place-2-first) (set? place-2-next)]}
  (and (one-different? place-1-first place-1-next)
       (one-different? place-2-first place-2-next)))

(defn one-step-different?
  [state1 state2]
  (let [[left-bank1 boat1 right-bank1] (map set state1)
        [left-bank2 boat2 right-bank2] (map set state2)]
    (if (and (move-away-from? [left-bank1 left-bank2] [boat1 boat2]))
      (= right-bank1 right-bank2)
      (if (and (move-away-from? [boat1 boat2] [right-bank1 right-bank2]))
        (= left-bank1 left-bank2)
        false))))

(defn valid-move?
  [state-first state-next] 
  (and (valid-state? state-first)
       (valid-state? state-next)
       (one-step-different? state-first state-next)))

(defn occurred-before?
  [steps step]
  (let [steps-set (map set steps)
        step-set (set step)]
    (contains? steps-set step-set)))

(defn get-you-index
  [state]
  (let [state-sets (map set state)
        set-with-you (first (filter person-present? state-sets))]
    (.indexOf state-sets set-with-you)))

(defn get-possible-moves-from-left-bank
  [left-bank-set-less-you boat-with-you]
  (vec (map (fn [item]
              (let [left-bank-less-you-and-item (disj left-bank-set-less-you
                                                      item)]
                [(vec left-bank-less-you-and-item)
                 (vec (conj boat-with-you item))]))
            left-bank-set-less-you)))

(defn go-to-boat-from-left-bank
  [left-bank boat]
  (let [left-bank-set (set left-bank)
        boat-set (set boat)
        left-bank-set-less-you (disj left-bank-set :you)
        boat-with-you (conj boat-set :you)]
    (get-possible-moves-from-left-bank left-bank-set-less-you
                                       boat-with-you)))

(defn go-to-right-bank-from-boat
  [boat right-bank]
  (let [boat-set (set boat)
        right-bank-set (set right-bank)
        boat-less-boat-and-you (disj boat-set :boat :you)
        item (first boat-less-boat-and-you)
        boat-next [:boat]
        right-bank-next (vec (conj right-bank-set :you item))]
    (if item
      [boat-next right-bank-next]
      [boat-next (vec (conj right-bank-set :you))])))

(defn go-to-left-bank-from-boat
  [left-bank boat]
  (let [left-bank-set (set left-bank)
        boat-set (set boat)
        boat-less-boat-and-you (disj boat-set :boat :you)
        boat-next [:boat]]
    (if (> (count boat-less-boat-and-you) 0)
      (let [item (first boat-less-boat-and-you)
            left-bank-next (vec (conj left-bank-set :you item))]
        [left-bank-next boat-next])
      (let [left-bank-next (vec (conj left-bank-set :you))]
        [left-bank-next boat-next]))))

(defn get-possible-moves-from-right-bank
  [boat-with-you right-bank-less-you]
  (map (fn [item]
         (let [right-bank-less-you-and-item (disj right-bank-less-you item)]
           [(vec (conj boat-with-you item))
            (vec right-bank-less-you-and-item)]))
       right-bank-less-you))

(defn go-to-boat-from-right-bank
  [boat right-bank]
  (let [boat-set (set boat)
        right-bank-set (set right-bank)
        right-bank-less-you (disj right-bank-set :you)
        boat-with-you (conj boat-set :you)]
    (let [possible-moves-with-items (get-possible-moves-from-right-bank
                                     boat-with-you
                                     right-bank-less-you)]
      (vec (concat [[(vec boat-with-you) (vec right-bank-less-you)]]
                   possible-moves-with-items)))))

(defn get-next-states
  [[left-bank boat right-bank :as state]]
  (let [you-index (get-you-index state)]
    (condp = you-index
      0 (vec (map #(vec (conj % right-bank))
                  (go-to-boat-from-left-bank left-bank boat)))
      1 (vector (conj (go-to-left-bank-from-boat left-bank boat)
                     right-bank)
                (vec (concat [left-bank]
                             (go-to-right-bank-from-boat boat right-bank))))
      2 (vec (map #(vec (concat [left-bank] %))
                  (go-to-boat-from-right-bank boat right-bank))))))

(defn occur-in?
  [items item]
  (boolean (some #(= (vec (map set item)) (vec (map set %))) items)))

(defn find-next-valid-states
  [states]
  (let [current-state (last states)
        next-states (get-next-states current-state)
        valid-states (vec (filter valid-state? next-states))
        not-occurred-before-states (vec (filter (partial (complement occur-in?)
                                                          states)
                                                 valid-states))
        ]
    not-occurred-before-states))

(defn advance-states
  [states-tree]
  (reduce (fn [new-states-tree state-sequence]
            (let [next-valid-states (find-next-valid-states state-sequence)]
              (if (empty? next-valid-states)
                (conj new-states-tree state-sequence)
                (loop [cur-valid-states next-valid-states
                       new-states []]
                  (if (empty? cur-valid-states)
                    (if (empty? new-states)
                      (conj new-states-tree state-sequence)
                      (vec (concat new-states-tree new-states)))
                    (let [cur-state (first cur-valid-states)]
                      (recur (vec (rest cur-valid-states))
                             (conj new-states (conj state-sequence
                                                    cur-state)))))))))
          []
          states-tree))

(defn get-sequence-with-solution
  [states-tree]
  (filter (fn [state-sequence] (= (last state-sequence) end)) states-tree))

(defn river-crossing-plan-recur
  [states-tree]
  (let [new-states-tree (advance-states states-tree)
        solution (first (get-sequence-with-solution new-states-tree))]
    (if (nil? solution)
      (if (= states-tree new-states-tree)
        "ERROR"
        (recur new-states-tree))
      solution)))

(defn river-crossing-plan []
  (let [states-tree [start-pos]]
    (river-crossing-plan-recur states-tree)))
