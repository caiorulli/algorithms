(ns algorithms.nqueens
  "N-Queens problem (https://en.wikipedia.org/wiki/Eight_queens_puzzle)")

(defn- valid-position?
  "Checks for queens in conflict elsewhere in the board,
  vertically or diagonally."
  [level [x & xs :as board] i]
  (or (empty? board)
      (let [distance (inc (- level (count board)))]
        (and (not= i x)
             (not= i (+ x distance))
             (not= i (- x distance))
             (recur level xs i)))))

(defn nqueens
  "First attempt, all combinations."
  ([n]
   (let [board '()]
     (nqueens n 0 board)))

  ([n level board]
   (if (= level n)
     1
     (->> (range n)
          (filter (partial valid-position? level board))
          (map #(nqueens n (inc level) (cons % board)))
          (reduce +)))))
