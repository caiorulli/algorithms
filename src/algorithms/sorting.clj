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
