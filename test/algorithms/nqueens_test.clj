(ns algorithms.nqueens-test
  (:require [midje.sweet :refer [fact =>]]
            [algorithms.nqueens :refer [nqueens
                                        nqueens+h]]))

(fact "N-Queens"
      (nqueens 0) => 1
      (nqueens 1) => 1
      (nqueens 2) => 0
      (nqueens 3) => 0
      (nqueens 4) => 2
      (nqueens 5) => 10
      (nqueens 6) => 4
      (nqueens 7) => 40
      (nqueens 8) => 92
      (nqueens 9) => 352)

(fact "N-Queens, no horizontal simmetries"
      (nqueens+h 0) => 1
      (nqueens+h 1) => 1
      (nqueens+h 2) => 0
      (nqueens+h 3) => 0
      (nqueens+h 4) => 2
      (nqueens+h 5) => 10
      (nqueens+h 6) => 4
      (nqueens+h 7) => 40
      (nqueens+h 8) => 92
      (nqueens+h 9) => 352)
