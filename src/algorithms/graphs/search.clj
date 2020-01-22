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

(defn dfs [graph source]
  (loop [search  [source]
         stack   (get graph source)
         visited #{source}]
    (if (empty? stack)
      search
      (let [u (first stack)]
        (if (contains? visited u)
          (recur search
                 (rest stack)
                 visited)
          (recur (conj search u)
                 (concat (get graph u)
                         (rest stack))
                 (conj visited u)))))))
