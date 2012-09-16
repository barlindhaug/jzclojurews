(ns fizzbuzz)

(defn fizzbuzz [number]
  (cond (= 0 (rem number 15)) "Fizzbuzz"
        (= 0 (rem number 3)) "Fizz"
        (= 0 (rem number 5)) "Buzz"
        :else number))

(defn fizzbuzzrepeat [to]
  (dotimes [n (+ to 1)]
    (println (fizzbuzz n))))