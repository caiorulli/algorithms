(ns algorithms.graphs.search-test
  (:require [algorithms.graphs.search :refer [bfs
                                              bfs+d
                                              dfs
                                              dfs+d]]
            [midje.sweet :refer [=> fact]]))

(fact "Breath first search"
      (bfs {0 [1 2 3]
            2 [4]} 0) => [0 1 2 3 4]
      (bfs {0 [1 2]} 0) => [0 1 2]
      (bfs {0 [1 2]
            1 [0 2]
            2 [0 1]} 0) => [0 1 2])

(fact "Breath first search with distance"
      (bfs+d {0 [1 2 3]
              2 [4]} 0) => {:elements [0 1 2 3 4]
                            :distance [0 1 1 1 2]}
      (bfs+d {0 [1 2]} 0) => {:elements [0 1 2]
                              :distance [0 1 1]}
      (bfs+d {0 [1 2]
              1 [0 2]
              2 [0 1]} 0) => {:elements [0 1 2]
                              :distance [0 1 1]})

(fact "Depth first search"
      (dfs {0 [1 2 3]
            2 [4]} 0) => [0 1 2 4 3]
      (dfs {0 [1 2]} 0) => [0 1 2]
      (dfs {0 [1 2]
            1 [0 2]
            2 [0 1]} 0) => [0 1 2])

(fact "Depth first search with distance"
      (dfs+d {0 [1 2 3]
              2 [4]} 0) => {:elements [0 1 2 4 3]
                            :distance [0 1 1 1 2]}
      (dfs+d {0 [1 2]} 0) => {:elements [0 1 2]
                              :distance [0 1 1]}
      (dfs+d {0 [1 2]
              1 [0 2]
              2 [0 1]} 0) => {:elements [0 1 2]
                              :distance [0 1 1]})
