(ns algorithms.sorting)

(defn- merge-lists [list-a list-b]
  (loop [result []
         rest-a list-a
         rest-b list-b]
    (let [a (first rest-a)
          b (first rest-b)]
      (if (not (or a b))
        result
        (if (or (nil? b)
                (and a (= a (min a b))))
          (recur (conj result a)
                 (rest rest-a)
                 rest-b)
          (recur (conj result b)
                 rest-a
                 (rest rest-b)))))))

(defn mergesort [list]
  (if (< (count list) 2)
    list
    (let [middle-index   (quot (count list) 2)
          leftmost-list  (-> list
                             (subvec 0 middle-index)
                             mergesort)
          rightmost-list (-> list
                             (subvec middle-index)
                             mergesort)]
      (merge-lists leftmost-list rightmost-list))))

(defn quicksort [list]
  (if (< (count list) 2)
    list
    (loop [pivot     0
           processed list
           current   (dec (count list))]
      (if (= pivot current)
        (concat (quicksort (subvec processed 0 pivot))
                (vector (get processed pivot))
                (quicksort (subvec processed (inc pivot) (count processed))))
        (let [current-value (get processed current)
              pivot-value   (get processed pivot)
              orientation   (- current pivot)]
          (if (pos? orientation)
            (if (< current-value pivot-value)
              (recur current
                     (assoc processed pivot current-value
                                      current pivot-value)
                     pivot)
              (recur pivot
                     processed
                     (dec current)))
            (if (> current-value pivot-value)
              (recur current
                     (assoc processed pivot current-value
                                      current pivot-value)
                     pivot)
              (recur pivot
                     processed
                     (inc current)))))))))
