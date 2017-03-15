(ns luhn.core)

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
  (->> (+
         (->> cc
              reverse
              odd-digits
              (apply +))
         (->> cc
              reverse
              even-digits
              (map double)
              (map sum-digits)
              (apply +)))
       zero-ended?))