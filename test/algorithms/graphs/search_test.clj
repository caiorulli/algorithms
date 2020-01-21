(ns algorithms.graphs.search-test
  (:require [algorithms.graphs.search :refer [bfs]]
            [midje.sweet :refer [=> fact]]))

(fact "Breath first search"
      (bfs {0 [1 2 3]
            2 [4]} 0) => [0 1 2 3 4]
      (bfs {0 [1 2]} 0) => [0 1 2]
      (bfs {0 [1 2]
            1 [0 2]
            2 [0 1]} 0) => [0 1 2])
