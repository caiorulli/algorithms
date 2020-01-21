(ns algorithms.graphs.search)

(defn bfs [graph source]
  (loop [search  [source]
         queue   (get graph source)
         visited #{source}]
    (if (empty? queue)
      search
      (let [u (first queue)]
        (if (contains? visited u)
          (recur search
                 (rest queue)
                 visited)
          (recur (conj search u)
                 (concat (rest queue)
                         (get graph u))
                 (conj visited u)))))))
