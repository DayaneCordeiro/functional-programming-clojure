;; podem ser criadas assim: 
(list 1 2 3 4 5) ; (1 2 3 4 5)

;; ou assim:
'(1 2 3 4 5) ; (1 2 3 4 5)
;; nesse caso, usa-se o apóstrofo por que depois de um ( a primeira coisa que se espera é uma função
;; sem o apóstrofo, o numero 1 seria compreendido como uma função e seria aplicado aos demais argumentos
;; o apóstrofo deixa claro para o avaliador que é só uma lista

;; listas podem ter nomes:
(def um-ate-5 '(1 2 3 4 5)) ; o def nomeia algo (como var ou const em outras linguagens)
                            ; #'user/um-ate-5

(count um-ate-5) ; diz quantos elementos tem em uma lista
                 ; 5

;; existe a função nativa range (não inclusiva, precisa colocar um numero a mais)
(def um-ate-15 (range 1 16)) ; #'user/um-ate-15

;; a função map aplica o fizzbuzz a cada elemento da lista
(map fizzbuzz um-ate-15) ; (1 2 "fizz" 4 "buzz" "fizz" 7 8 "fizz" "buzz" 11 "fizz" 13 14 "fizzbuzz")