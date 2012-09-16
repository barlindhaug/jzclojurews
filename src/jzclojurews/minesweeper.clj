(ns minesweeper
  (:use clojure.test))

(def board [[nil   :mine nil   nil  ]
            [:mine nil   nil   nil  ]
            [nil   nil   :mine nil  ]
            [:mine nil   nil   :mine]])



(with-test
    (defn mine?
      "Returns true if cell c contains a mine, false otherwise."
      [board c]
      (if (or 
            (or (>= (first c) (count board)) (< (first c) 0))
            (or (>= (second c) (count (board 0))) (< (second c) 0)))
        false
        (if (= ((board (first c)) (second c)) :mine)
          true
          false)))
  
  (is (true? (mine? board [0 1])))
  (is (false? (mine? board [0 2])))
  (is (false? (mine? board [4 3])))
  (is (false? (mine? board [-1 -1])))
  (is (false? (mine? board [13 37]))))

(with-test
    (defn neighbours
      "For a cell c, return a list of the cell's neighbours."
      [c]
      (for [x [-1 0 1] y [-1 0 1]
        :when (or (not= x 0) (not= y 0))]
        [(+ (first c) x) (+ (second c) y)]))

  (is (= [[-1 -1] [-1 0] [-1 1] [0 -1] [0 1] [1 -1] [1 0] [1 1]]
         (neighbours [0 0])))
  (is (= [[1 2] [1 3] [1 4] [2 2] [2 4] [3 2] [3 3] [3 4]]
         (neighbours [2 3])))
)

(with-test
    (defn mines
      "For a cell c, return the number of mines adjacent to it."
      [board c]
      (let [mines (find
        (frequencies 
          (map (fn [n] (mine? board n)) (neighbours c)))
        true)]
      (if mines
        (second mines)
        0)))

  (is (= 3 (mines board [1 1])))
  (is (= 0 (mines board [0 3])))
  (is (= 2 (mines board [3 2])))
)