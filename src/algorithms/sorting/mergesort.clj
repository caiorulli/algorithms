(ns algorithms.sorting.mergesort)

(defn merge [list-a list-b]
  (loop [result []
         rest-a list-a
         rest-b list-b]
    (if (and (empty? rest-a)
             (empty? rest-b))
      result
      (recur (conj result (min (first rest-a)
                               (first rest-b)))
             (rest rest-a)
             (rest rest-b)))))

(defn mergesort [list]
  (if (< (count list) 2)
    list
    (let [middle-index (quot (count list) 2)
          leftmost-list (-> list
                            (subvec 0 middle-index)
                            mergesort)
          rightmost-list (-> list
                             (subvec middle-index)
                             mergesort)]
      (merge leftmost-list rightmost-list))))
