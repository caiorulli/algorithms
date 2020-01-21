(ns algorithms.nqueens-test
  (:require [midje.sweet :refer [future-fact =>]]
            [algorithms.nqueens :refer [nqueens]]))

(future-fact "N-Queens"
             (nqueens 4) => [[2 4 1 3]
                             [3 1 4 2]])
