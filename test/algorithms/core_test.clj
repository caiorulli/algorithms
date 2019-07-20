(ns algorithms.core-test
  (:require [midje.sweet :refer [=> fact facts]]))

(facts "a-test"
  (fact "lala"
    1 => 1))
