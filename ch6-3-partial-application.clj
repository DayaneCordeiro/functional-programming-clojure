;; revendo a função que converte de real para yuan:
(defn transacao-em-yuan [transacao]
  (let [yuan (:yuan cotacoes)]
    (assoc transacao :valor (* (:cotacao yuan) (:valor transacao))
           :moeda (:simbolo yuan))))

(transacao-em-yuan (first transacoes)) ; {:valor 70.950M, :tipo "despesa", :comentatio "Almoço", :moeda "¥", :data "19/11/2016"}

;; em Clojure existe um recurso chamado destructing que permite aprimorar o uso do let
;; quando é necessário extrair os valores de um argumento que é um mapa
;; ele associa os nomes a valores de chaves, da mesma forma que o let faz, mas de uma forma mais simples

;; esse tipo de recurso, ajuda na reutilização de código
(defn transacao-em-yuan [transacao]
  (let [{{cotacao :cotacao simbolo :simbolo} :yuan} cotacoes]
    (assoc transacao :valor (* cotacao (:valor transacao))
           :moeda simbolo)))

(transacao-em-yuan (first transacoes)) ; {:valor 70.950M, :tipo "despesa", :comentatio "Almoço", :moeda "¥", :data "19/11/2016"}