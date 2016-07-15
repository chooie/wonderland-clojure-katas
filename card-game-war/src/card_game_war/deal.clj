(ns card-game-war.deal)

(defn- partitionv
  [n coll]
  (mapv vec (partition n coll)))

(defn- zip-with
  [colls]
  (apply mapv vector colls))

(defn deal-cards
  [cards number-of-players]
  (let [hand-dealings (partitionv number-of-players cards)
        player-hands (zip-with hand-dealings)]
    player-hands))
