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

;; ------------------------------------------------
;; Crie um vector com 6 números e:
;Retorne o primeiro
;Retorne o último
;Retorne os 3 primeiros

(vector 1 2 3 4 5 6)
(def my-vector (vector 1 2 3 4 5 6))

(get my-vector 0) ; 1
(first my-vector) ; 1

(last my-vector) ; 6

(take 3 my-vector) ; (1 2 3)
(subvec my-vector 0 3) ; [1 2 3]

;; Dado:
; (def users ["Ana" "Bruno" "Carlos" "Diana"])
;Adicione "Eduardo"
;Remova "Carlos"
;Substitua "Bruno" por "Breno"

(def users ["Ana" "Bruno" "Carlos" "Diana"])

;Adicione "Eduardo"
(conj users "Eduardo") ; ["Ana" "Bruno" "Carlos" "Diana" "Eduardo"]

; ----- ;Remova "Carlos"
(def updated-users
  (vec (remove #(= % "Carlos") users)))

;; função anônima # que percorre uma coleção tentando ver se o elemento atual % é = a "Carlos"
#(= % "Carlos")

;; se for remove de users, porém retorna uma lista
(remove #(= % "Carlos") users) ; ("Ana" "Bruno" "Diana")

;; volta para um vetor
(vec (remove #(= % "Carlos") users)) ; ["Ana" "Bruno" "Diana"]

;; joga em uma nova função para ter uma nova coleção sem "carlos"
(def updated-users
  (vec (remove #(= % "Carlos") users)))
; -----

;Substitua "Bruno" por "Breno"
(vec (assoc users (.indexOf users "Bruno") "Breno")) ; ["Ana" "Breno" "Carlos" "Diana"]