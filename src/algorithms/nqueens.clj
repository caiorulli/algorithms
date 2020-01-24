(ns algorithms.nqueens)

(defn nqueens
  ([n]
   (let [board []]
     (nqueens n 0 board)))

  ([n level board]
   (if (= level n)
     1
     (reduce +
             (for [i (range n)]
               (if (some
                    (fn [[j queen]]
                      (contains? #{i (+ i (inc j)) (- i (inc j))}
                                 queen))
                    (zipmap (range level) board))
                 0
                 (nqueens n
                          (inc level)
                          (cons i board))))))))
