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

(defn bfs+d [graph source]
  (loop [search   []
         queue    [source]
         visited  #{}
         queued   #{source}
         distance [0]]
    (if (empty? queue)
      {:elements search
       :distance distance}
      (let [u (first queue)
            v (->> (get graph u)
                   (filter (comp not queued))
                   (filter (comp not visited)))]
        (recur (conj search u)
               (concat (rest queue) v)
               (conj visited u)
               (set (concat queued v))
               (concat distance (repeat (count v)
                                        (inc (last distance)))))))))


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

(defn dfs+d [graph source]
  (loop [search   []
         stack    [source]
         visited  #{}
         stacked  #{source}
         distance [0]]
    (if (empty? stack)
      {:elements search
       :distance distance}
      (let [u (first stack)
            v (->> (get graph u)
                   (filter (comp not stacked))
                   (filter (comp not visited)))]
        (recur (conj search u)
               (concat v (rest stack))
               (conj visited u)
               (set (concat stacked v))
               (concat distance (repeat (count v)
                                        (inc (last distance)))))))))
