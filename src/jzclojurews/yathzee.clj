(ns yathzee (:use clojure.test))


(with-test 
  (defn chance [dices]
    (reduce + dices))
  (is (= 20 (chance [2 3 6 6 3]))))

(with-test 
  (defn simple [dices digit]
    (reduce +
      (filter (fn [number] (= number digit)) dices)))
  (is (= 0 (simple [2 2 3 4 5] 1)) "No matches")
  (is (= 4 (simple [2 2 3 4 5] 2)) "Two twos")
)

(with-test
  (defn several-equals [dices num-equals]
    (let [pairs (filter (fn [number] (>= (second number) num-equals)) (frequencies dices))]
      (if (empty? pairs) 0
        (* num-equals (reduce max (map #(first %) pairs))))))
    (is (= 0 (several-equals [6 2 3 4 5] 2)) "No pairs")
    (is (= 4 (several-equals [2 2 3 4 5] 2)) "One pair")
    (is (= 12 (several-equals [2 2 6 6 5] 2)) "Highest pair")
    (is (= 12 (several-equals [2 6 6 6 5] 2)) "Three equals is also a pair")
    (is (= 24 (several-equals [2 6 6 6 6] 4)) "Four equals")
    (is (= 0 (several-equals [2 6 6 6 5] 4)) "Not four equal")
)

