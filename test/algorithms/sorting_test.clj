(ns algorithms.sorting-test
  (:require [algorithms.sorting :refer [mergesort
                                        quicksort
                                        bogosort
                                        bubblesort
                                        heapsort]]
            [midje.sweet :refer [tabular fact =>]]))

(tabular "about sorting"
  (fact
    (?fn [])              => []
    (?fn [1])             => [1]
    (?fn [2 1 3 4])       => [1 2 3 4]
    (?fn [6 7 234 1 6])   => [1 6 6 7 234]
    (?fn [7 6 5 4 3 2 1]) => [1 2 3 4 5 6 7])
  [?fn] mergesort quicksort bogosort bubblesort heapsort)
