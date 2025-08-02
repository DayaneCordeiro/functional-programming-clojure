;; vetores são como arrays, começam do zero e podem guardar qualquer tipo de valor, permitindo busca
;; pelo índice. Vetores são mais eficientes para adicionar elementos no final, enquanto as listas no
;; começo. Nas listas não da pra pegar uma posição específica, é necessário usar o nth. Como isso faz
;; ser necessário percorrer cada elemento da lista, tem uma perda de desempenho.

;; criando um vetor
(vector 1 2 3 4 5) ; [1 2 3 4 5]

;; ou apenas
[1 2 3 4 5]

;; com def
(def numeros-vetorizados [1 2 3 4 5]) ; #'user/numeros-vetorizados

;; muitas coisas funcionam tanto com vetores como com listas
(map fizzbuzz numeros-vetorizados) ; (1 2 "fizz" 4 "buzz")

;; manipulando vetores: =====================
(def cantor-arretado (vector "Chico César" "Catolé do Rocha" 26 "janeiro" 1964)) ; #'user/cantor-arretado

(get cantor-arretado 0) ; "Chico César"

(get cantor-arretado 4) ; 1964

(last cantor-arretado) ; 1964

; incluindo um novo elemento (porém as estruturas de dados são imutáveis):
(conj cantor-arretado "MPB") ; ["Chico César" "Catolé do Rocha" 26 "janeiro" 1964 "MPB"]

; vendo o estado atual:
cantor-arretado ; ["Chico César" "Catolé do Rocha" 26 "janeiro" 1964]