(ns clojure-spellchecker.core
  (:import (org.apache.commons.lang3 StringUtils))
  (:require [clojure.string :as str]))

(def words
  (set (map str/trim (str/split-lines (slurp "resources/words.txt")))))

(defn distance [word1 word2]
  (StringUtils/getLevenshteinDistance word1 word2))

(defn correct? [word] 
  (contains? words word))

(defn min-distance [word]
  (apply min-key (partial distance word) words))

(defn -main [& args]
  (let [word (first args)]
    (if (correct? word)
      (println "correct")
      (println "Did you mean " (min-distance word) "?"))))
