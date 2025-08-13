;; novo formato de transações com a moeda
(def transacoes
  [{:valor 33.0 :tipo "despesa" :comentatio "Almoço" :moeda "R$" :data "19/11/2016"}
   {:valor 2700.0 :tipo "receita" :comentatio "Bico" :moeda "R$" :data "01/12/2016"}
   {:valor 29.0 :tipo "despesa" :comentatio "Livro de Clojure" :moeda "R$" :data "03/12/2016"}]) ; #'user/transacoes

;; função que pega o valor de uma transação e coloca + ou - antes (minha versão)
(defn valor-sinalizado [transacao]
  (if (= (:tipo transacao) "despesa") 
    (str "-" (:valor transacao))
    (str "+" (:valor transacao)))) ; #'user/valor-sinalizado

(valor-sinalizado (first transacoes)) ; "-33.0"

;; agora adicionando a moeda na função de valor sinalizado:
(defn valor-sinalizado [transacao]
  (if (= (:tipo transacao) "despesa")
    (str (:moeda transacao) " -" (:valor transacao))
    (str (:moeda transacao) " +" (:valor transacao)))) ; #'user/valor-sinalizado

(valor-sinalizado (first transacoes)) ; "R$ -33.0"

;; só que neste caso, a forma de pegar o valor e a moeda estão duplicados, no clojure, tem o let que
;; pode ajudar a resolver esse problema
(defn valor-sinalizado [transacao]
      (let [moeda (:moeda transacao)
            valor (:valor transacao)]
        (if (= (:tipo transacao) "despesa")
          (str moeda " -" valor)
          (str moeda " +" valor)))) ; #'user/valor-sinalizado

(valor-sinalizado (first transacoes)) ; "R$ -33.0"

;; Gera uma versão em texto do resumo de uma transação
(texto-resumo uma-transacao-qualquer) ; "01/12/2016 => R$ +2700.00"

