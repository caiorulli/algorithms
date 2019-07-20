(ns algorithms.sorting
  (:require [algorithms.sorting.mergesort :as sut]
            [midje.sweet :refer [facts fact =>]]))

(facts "mergesort"
  (fact "returns empty list from empty list"
    (sut/mergesort []) => [])

  (fact "returns single element list if list has only one element"
    (sut/mergesort [1]) => [1])

  (fact "returns sorted list when provided with list larger than one element"
    (sut/mergesort [2 1 3 4]) => [1 2 3 4]))

