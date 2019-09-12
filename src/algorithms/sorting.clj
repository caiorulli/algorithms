(ns algorithms.sorting
  (:require [clojure.spec.alpha :as s]))

(defn- merge-lists [list-a list-b]
  (loop [result []
         rest-a list-a
         rest-b list-b]
    (let [a (first rest-a)
          b (first rest-b)]
      (if-not (or a b)
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

(defn- is-sorted? [list]
  (or (empty? list)
      (apply <= list)))

(defn bogosort
  "Best sorting algorithm ever."
  [list]
  (if (is-sorted? list)
    list
    (let [[a b] (repeatedly 2 #(rand-int (count list)))]
      (recur (assoc list a (get list b)
                         b (get list a))))))

(defn- iterate-bubble
  [limit result]
  (loop [current   0
         processed result]
    (if (= current limit)
      processed
      (let [next          (inc current)
            current-value (get processed current)
            next-value    (get processed next)]
        (if (> current-value
               next-value)
          (recur
            (inc current)
            (assoc processed current next-value
                             next current-value))
          (recur
            (inc current)
            processed))))))

(defn bubblesort [list]
  (if (empty? list)
    list
    (loop [limit  (dec (count list))
           result list]
      (if (zero? limit)
        result
        (recur
          (dec limit)
          (iterate-bubble limit result))))))

(defn- balanced-heap-at-pos
  [current list]
  (let [root        (get list current)
        left        (inc (* 2 current))
        left-child  (get list left)
        right       (* 2 (inc current))
        right-child (get list right)]
    (condp = (->> [root left-child right-child]
                  (filter identity)
                  (apply max))
      root list

      left-child (recur left
                        (assoc list current left-child
                                    left root))

      right-child (recur right
                         (assoc list current right-child
                                     right root)))))

(defn- max-heap [list]
  (loop [current   (dec (int (Math/floor (/ (count list) 2))))
         processed list]
    (if (neg? current)
      processed
      (recur (dec current)
             (balanced-heap-at-pos current processed)))))

(defn heapsort [list]
  (loop [current   (count list)
         processed list]
    (if (<= current 1)
      processed
      (let [heap     (max-heap (subvec processed 0 current))
            last-val (dec current)]
        (recur last-val
               (vec (concat (assoc heap 0 (get heap last-val)
                                        last-val (get heap 0))
                            (subvec processed current))))))))

(defn- insert
  [position list]
  (if (< position 1)
    list
    (let [pos-value  (get list position)
          previous   (dec position)
          prev-value (get list previous)]
      (if (< prev-value pos-value)
        list
        (recur previous
               (assoc list previous pos-value
                           position prev-value))))))

(defn insertion-sort [list]
  (if (< (count list) 2)
    list
    (loop [current   1
           processed list]
      (if (= current (count processed))
        processed
        (recur (inc current)
               (insert current processed))))))

(defn- find-min
  [position list]
  (loop [current      (inc position)
         min-position position]
    (if (= current (count list))
      (assoc list position (get list min-position)
                  min-position (get list position))
      (let [current-value (get list current)
            min-value     (get list min-position)]
        (if (< current-value min-value)
          (recur (inc current) current)
          (recur (inc current) min-position))))))

(defn selection-sort [list]
  (if (< (count list) 2)
    list
    (loop [current   0
           processed list]
      (if (= current (dec (count processed)))
        processed
        (recur (inc current)
               (find-min current processed))))))

;; attempts at generative testing
(s/fdef mergesort
  :args (s/cat :list (s/coll-of number? :kind vector?))
  :ret (s/coll-of number? :kind vector?)
  :fn #(= (:ret %) (-> % :args :list sort)))

(s/fdef quicksort
  :args (s/cat :list (s/coll-of number? :kind vector?))
  :ret (s/coll-of number? :kind vector?)
  :fn #(= (:ret %) (-> % :args :list sort)))
