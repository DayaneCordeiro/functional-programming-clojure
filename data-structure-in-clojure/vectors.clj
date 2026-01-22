(ns vectors)

(vector 1 2 3 4 5)

[1 2 3]

(def numeros-vetorizados [1 2 3 4 5])

;; adiciona um valor no final do vetor
(conj numeros-vetorizados 6) ;; [1 2 3 4 5 6]

;; acessa pelo índice
(get numeros-vetorizados 4) ;; 5
(nth numeros-vetorizados 4) ;; 5

;; substitui o valor do índice que foi passado
(assoc [10 20] 0 99) ;; [99 20]
(assoc numeros-vetorizados 1 20) ;; [1 20 3 4 5]

;; remove o último elemento
(pop numeros-vetorizados) ;; [1 2 3 4]