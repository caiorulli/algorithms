(ns algorithms.sorting-test
  (:require [algorithms.sorting :refer [mergesort]]
            [midje.sweet :refer [facts fact =>]]))

(facts "about sorting"
  (fact "mergesort"
    (mergesort []) => []
    (mergesort [1]) => [1]
    (mergesort [2 1 3 4]) => [1 2 3 4]
    (mergesort [6 7 234 1 6]) => [1 6 6 7 234]))

