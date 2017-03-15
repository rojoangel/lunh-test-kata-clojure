(ns luhn.core-test
  (:use midje.sweet)
  (:use [luhn.core]))

(defn- sum-digits [n]
  (apply + (map (comp read-string str) (str n))))

(defn- char->number [ch]
  (read-string (str ch)))

(defn- odds [xs]
  (take-nth 2 xs))

(defn- odd-digits [xs]
  (->> xs
       odds
       (map char->number)))

(defn- evens [xs]
  (take-nth 2 (rest xs)))

(defn valid? [cc]
  (let [reversed-cc (reverse cc)
        odd-digits-sum (apply + (odd-digits reversed-cc))
        even-digits-calc (apply + (map sum-digits (map #(* 2 %) (map char->number(evens reversed-cc)))))]
    (= 0 (rem (+ odd-digits-sum even-digits-calc) 10))))

(facts "about luhn test"
       (fact "valid CC nums pass the test"
             (valid? "49927398716") => true))
