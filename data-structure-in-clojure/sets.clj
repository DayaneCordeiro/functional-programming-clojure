(ns sets
    (:require [clojure.set :as set]))

#{1 2 3 4 5}
(hash-set 1 2 3 4 5)
(def meu-set #{1 2 3 4 5})

;; adiciona um elemento se ainda não existir
(conj meu-set 8) ;; #{1 4 3 2 5 8}
(conj meu-set 1) ;; #{1 4 3 2 5}

(def meu-set-2 (conj meu-set 8)) ;; #'user/meu-set-2
meu-set-2 ;; #{1 4 3 2 5 8}

;; removendo um elemento específico
(disj meu-set 2) ;; #{1 4 3 5}

;; verifica se existe um valor no set
(contains? meu-set 1) ;; true
(contains? meu-set 9) ;; false

;; 1. Definindo dois conjuntos com valores mistos
(def conj-a #{1 2 "a"})
(def conj-b #{2 "b" 5})

;; 2. Unindo os dois
(def resultado (set/union conj-a conj-b))

;; O resultado será: #{1 2 5 "a" "b"}
;; Note que o valor '2', que estava em ambos, aparece apenas uma vez.
(println resultado)