;; Sintaxe
(list 1 2 3 4 5)

'(1 2 3 4 5)

;; lista nomeada -> é algo parecido com declarar uma variável
(def um-ate-5 '(1 2 3 4 5))

;; conta quantos elementos tem em uma lista
(count um-ate-5) ;; 5

;; criar uma lista numérica com a função nativa range
(def um-ate-15 (range 1 16)) ;; #'user/um-ate-15

;; aplicando a função fizzbuzz a cada elemento da lista criada com range
(map fizzbuzz um-ate-15)
;; (1 2 "fizz" 4 "buzz" "fizz" 7 8 "fizz" "buzz" 11 "fizz" 13 14 "fizzbuzz")

;; Adicionando um elemento no começo da lista
(conj '(2 3) 1) ;; (1 2 3)

(conj um-ate-5 7) ;; (7 1 2 3 4 5)

;; remove o elemento no topo da lista
(pop um-ate-5) ;; (2 3 4 5)

;; retorna o primeiro elemento da lista
(peek um-ate-5) ;; 1
(first um-ate-5) ;; 1

;; retorna a lista sem o primeiro elemento
(rest um-ate-5) ;; (2 3 4 5)

;; pegar uma posição especifica da lista
(nth um-ate-5 2)