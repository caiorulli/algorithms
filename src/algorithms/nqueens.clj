(ns algorithms.nqueens
  "N-Queens problem (https://en.wikipedia.org/wiki/Eight_queens_puzzle)")

(defn- invalid-position?
  "Checks for queens in conflict elsewhere in the board,
  vertically or diagonally."
  [i level board]
  (some
   (fn [[j queen]]
     (contains? #{i (+ i (inc j)) (- i (inc j))}
                queen))
   (zipmap (range level) board)))

(defn nqueens
  "First attempt, all combinations."
  ([n]
   (let [board []]
     (nqueens n 0 board)))

  ([n level board]
   (if (= level n)
     1
     (reduce +
             (for [i (range n)]
               (if (invalid-position? i level board)
                 0
                 (nqueens n
                          (inc level)
                          (cons i board))))))))

(defn nqueens+h
  "Removing horizontal simmetries"
  ([n]
   (let [board []]
     (nqueens n 0 board)))

  ([n level board]
   (if (= level n)
     1
     (reduce +
             (for [i (range n)]
               (if (or (and (= level 0)
                            (> i (/ n 2)))
                       (invalid-position? i level board))
                 0
                 (nqueens n
                          (inc level)
                          (cons i board))))))))
