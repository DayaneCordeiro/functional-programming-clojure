;; cria o resumo da transação
(defn resumo [transacao]
  (select-keys transacao [:valor :tipo :data])) ; #'user/resumo

;; algumas transacoes
(def transacoes
  [{:valor 33.0 :tipo "despesa" :comentatio "Almoço" :data "19/11/2016"}
   {:valor 2700.0 :tipo "receita" :comentatio "AlmBicooço" :data "01/12/2016"}
   {:valor 29.0 :tipo "despesa" :comentatio "Livro de Clojure" :data "03/12/2016"}]) ; #'user/transacoes

(map resumo transacoes) ; ({:valor 33.0, :tipo "despesa", :data "19/11/2016"} 
                        ; {:valor 2700.0, :tipo "receita", :data "01/12/2016"} 
                        ; {:valor 29.0, :tipo "despesa", :data "03/12/2016"})