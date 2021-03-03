(ns algorithms.nqueens
  "N-Queens problem (https://en.wikipedia.org/wiki/Eight_queens_puzzle)")

(defn- valid-position?
  "Checks for queens in conflict elsewhere in the board,
  vertically or diagonally."
  [level board i]
  (let [board-len (count board)]
    (or (zero? board-len)
        (let [distance (inc (- level (count board)))
              x        (nth board (dec board-len))
              xs       (pop board)]
          (and (not= i x)
               (not= i (+ x distance))
               (not= i (- x distance))
               (recur level xs i))))))

(defn nqueens
  "First attempt, all combinations."
  ([n]
   (let [board []]
     (nqueens n 0 board)))

  ([n level board]
   (if (= level n)
     1
     (->> (range n)
          (filter (partial valid-position? level board))
          (map #(nqueens n (inc level) (conj board %)))
          (reduce +)))))
