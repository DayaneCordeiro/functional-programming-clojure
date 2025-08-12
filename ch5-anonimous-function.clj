;; algumas transacoes
(def transacoes
  [{:valor 33.0 :tipo "despesa" :comentatio "Almoço" :data "19/11/2016"}
   {:valor 2700.0 :tipo "receita" :comentatio "AlmBicooço" :data "01/12/2016"}
   {:valor 29.0 :tipo "despesa" :comentatio "Livro de Clojure" :data "03/12/2016"}]) ; #'user/transacoes

;; verifica se o valor é maior que 100
(defn valor-grande? [transacao]
  (> (:valor transacao) 100)) ; #'user/valor-grande?

(filter valor-grande? transacoes) ; ({:valor 2700.0, :tipo "receita", :comentatio "AlmBicooço", :data "01/12/2016"})

;; uma função anônima que não recebe nenhum argumento e não faz nada:
(fn []) ; #object[user$eval2140$fn__2141 0xdd34751 "user$eval2140$fn__2141@dd34751"]

;; recebe um nome e retorna uma mensagem. Porém essa fn fica num limbo, pois não tem como referenciá-la
(fn [nome]
  (str "Olá, ", nome "!")) ; #object[user$eval2144$fn__2145 0x118a58f7 "user$eval2144$fn__2145@118a58f7"]

;; ela precisaria já ser executada no moneto da criação:
((fn [nome]
   (str "Olá, " nome "!"))
 "mundo novo") ; "Olá, mundo novo!"

;; então fazendo o filter de valores grandes com fn anônimas:
(filter (fn [transacao]
          (> (:valor transacao) 100))
        transacoes) ; ({:valor 2700.0, :tipo "receita", :comentatio "AlmBicooço", :data "01/12/2016"})

;; mas não é necessário usar fn para criar uma função anônima, pode se utilizar #() também
;; nesse tipo de declaração, os argumentos não precisam ser nomeados, podendo utilizar apenas o % ou
;; %1, %2... quando existem mais argumentos
(filter #(> (:valor %) 100)
        transacoes) ; ({:valor 2700.0, :tipo "receita", :comentatio "AlmBicooço", :data "01/12/2016"})

;; tendo em vista como as funções anônimas funcionam, é possível pegar o código antigo:
(defn despesa? [transacao]
  (= (:tipo transacao) "despesa")) 

(defn valor [transacao]
  (:valor transacao)) 

(reduce + (map valor (filter despesa? transacoes)))

;; e condensar nisso:
(reduce + (map #(:valor %)
               (filter #(= (:tipo %) "despesa")
                       transacoes))) ;; 62.0