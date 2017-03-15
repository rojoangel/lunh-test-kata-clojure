(ns luhn.core-test
  (:use midje.sweet)
  (:use [luhn.core]))

(facts "about luhn test"
       (fact "valid CC nums pass the test"
             (valid? "49927398716") => true))
