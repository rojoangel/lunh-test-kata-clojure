(ns luhn.core-test
  (:use midje.sweet)
  (:use [luhn.core]))

(defn- sum-digits [n]
  (apply + (map (comp read-string str) (str n))))

(defn- odds [xs]
  (take-nth 2 xs))

(defn- evens [xs]
  (take-nth 2 (rest xs)))

(defn valid? [cc]
  (let [reversed-cc (reverse cc)
        odd-digits-sum (apply + (map (comp read-string str) (odds reversed-cc)))
        even-digits-calc (apply + (map sum-digits (map (comp #(* 2 %) (comp read-string str)) (evens reversed-cc))))]
    (= 0 (rem (+ odd-digits-sum even-digits-calc) 10))))

(facts "about luhn test"
       (fact "valid CC nums pass the test"
             (valid? "49927398716") => true))
