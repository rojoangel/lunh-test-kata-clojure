(ns luhn.core-test
  (:use midje.sweet)
  (:use [luhn.core]))

(defn- double [n]
  (* 2 n))

(defn- char->number [ch]
  (read-string (str ch)))

(defn- sum-digits [n]
  (->> (str n)
       (map char->number)
       (apply +)))

(defn- odds [xs]
  (take-nth 2 xs))

(defn- evens [xs]
  (take-nth 2 (rest xs)))

(defn- odd-digits [xs]
  (->> xs
       odds
       (map char->number)))

(defn- even-digits [xs]
  (->> xs
       evens
       (map char->number)))

(defn valid? [cc]
  (let [reversed-cc (reverse cc)
        odd-digits-sum (apply + (odd-digits reversed-cc))
        even-digits-calc (apply + (map sum-digits (map double (even-digits reversed-cc))))]
    (= 0 (rem (+ odd-digits-sum even-digits-calc) 10))))

(facts "about luhn test"
       (fact "valid CC nums pass the test"
             (valid? "49927398716") => true))
