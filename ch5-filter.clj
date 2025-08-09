;; verifica se o tipo da transação é despesa pela chave :tipo
(defn despesa? [transacao]
  (= (:tipo transacao) "despesa")) ; #'user/despesa?

;; algumas transacoes
(def transacoes
  [{:valor 33.0 :tipo "despesa" :comentatio "Almoço" :data "19/11/2016"}
   {:valor 2700.0 :tipo "receita" :comentatio "AlmBicooço" :data "01/12/2016"}
   {:valor 29.0 :tipo "despesa" :comentatio "Livro de Clojure" :data "03/12/2016"}]) ; #'user/transacoes

;; filtrando as despesas dentre as transações
(filter despesa? transacoes) ; ({:valor 33.0, :tipo "despesa", :comentatio "Almoço", :data "19/11/2016"}
                             ;  {:valor 29.0, :tipo "despesa", :comentatio "Livro de Clojure", :data "03/12/2016"})