(ns luhn.core-test
  (:use midje.sweet)
  (:use [luhn.core]))

(defn- double [n]
  (->> n
       (* 2)))

(defn- char->number [ch]
  (->> ch
       str
       read-string))

(defn- sum-digits [n]
  (->> n
       str
       (map char->number)
       (apply +)))

(defn- odds [xs]
  (->> xs
       (take-nth 2)))

(defn- evens [xs]
  (->> xs
       rest
       (take-nth 2)))

(defn- odd-digits [xs]
  (->> xs
       odds
       (map char->number)))

(defn- even-digits [xs]
  (->> xs
       evens
       (map char->number)))

(defn- zero-ended? [n]
  (-> n
      (rem 10)
      (= 0)))

(defn valid? [cc]
  (let [odd-digits-sum (apply + (odd-digits (reverse cc)))
        even-digits-calc (apply + (map sum-digits (map double (even-digits (reverse cc)))))]
    (zero-ended? (+ odd-digits-sum even-digits-calc))))

(facts "about luhn test"
       (fact "valid CC nums pass the test"
             (valid? "49927398716") => true))
