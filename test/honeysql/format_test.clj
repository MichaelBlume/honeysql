(ns honeysql.format-test
  (:refer-clojure :exclude [format])
  (:require [clojure.test :refer [deftest testing is are]]
            [honeysql.format :refer :all]))

(deftest test-quote
  (are
    [qx res]
    (= (apply quote-identifier "foo.bar.baz" qx) res)
    [] "foo.bar.baz"
    [:style :mysql] "`foo`.`bar`.`baz`"
    [:style :mysql :split false] "`foo.bar.baz`")
  (are
    [x res]
    (= (quote-identifier x) res)
    3 "3"
    'foo "foo"
    :foo-bar "foo_bar")
  (is (= (quote-identifier "*" :style :ansi) "*")))

