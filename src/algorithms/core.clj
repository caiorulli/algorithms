(ns algorithms.core
  (:require [algorithms.nqueens :refer [nqueens]])
  (:gen-class))

(defn -main []
  (println (nqueens 15)))
