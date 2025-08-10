;; verifica se o tipo da transação é despesa pela chave :tipo
(defn despesa? [transacao]
  (= (:tipo transacao) "despesa")) ; #'user/despesa?

;; algumas transacoes
(def transacoes
  [{:valor 33.0 :tipo "despesa" :comentatio "Almoço" :data "19/11/2016"}
   {:valor 2700.0 :tipo "receita" :comentatio "AlmBicooço" :data "01/12/2016"}
   {:valor 29.0 :tipo "despesa" :comentatio "Livro de Clojure" :data "03/12/2016"}]) ; #'user/transacoes

;; pega o campo valor da transacao
(defn valor [transacao]
  (:valor transacao)) ; #'user/valor

;; pega a função valor e aplica à lista de despesas, de forma que vai mapear todos os valores
;; de transações que são despesas
(map valor (filter despesa? transacoes)) ; (33.0 29.0)

;; aplica a operação de soma sobre as despesas
;; reduce começa com nil, aplica nesse caso a função + ao primeiro elemento da lista e guarda em um 
;; acumulador
(reduce + (map valor (filter despesa? transacoes))) ; 62.0