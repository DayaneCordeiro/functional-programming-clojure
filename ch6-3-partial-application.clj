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

;; visando a possibilidade de reutilizar a mesma função para outras moedas
(def cotacoes
  {:yuan {:cotacao 2.15M :simbolo "¥"}
   :euro {:cotacao 0.28M :simbolo "€"}})

;; a moeda agora é passada por parâmetro
(defn transacao-em-outra-moeda [moeda transacao]
  (let [{{cotacao :cotacao simbolo :simbolo} moeda} cotacoes]
    (assoc transacao :valor (* cotacao (:valor transacao))
           :moeda simbolo)))

;; o simbolo do euro bugou no terminal
(transacao-em-outra-moeda :euro (first transacoes)) ; {:valor 9.240M, :tipo "despesa", :comentatio "Almoço", :moeda "?", :data "19/11/2016"}

(transacao-em-outra-moeda :euro (last transacoes)) ; {:valor 8.120M, :tipo "despesa", :comentatio "Livro de Clojure", :moeda "?", :data "03/12/2016"}

(transacao-em-outra-moeda :yuan (first transacoes)) ; {:valor 70.950M, :tipo "despesa", :comentatio "Almoço", :moeda "¥", :data "19/11/2016"}

(transacao-em-outra-moeda :yuan (last transacoes)) ; {:valor 62.350M, :tipo "despesa", :comentatio "Livro de Clojure", :moeda "¥", :data "03/12/2016"}

;; a unica coisa que muda de uma chamada para outra é a moeda, a aplicação de funções parciais
;; permite pegar uma função com mais parâmetros e passar menos coisas pra ela
(def transacao-em-euro (partial transacao-em-outra-moeda :euro)) ;; a moeda ja vai embutida

(def transacao-em-yuan (partial transacao-em-outra-moeda :yuan))

(transacao-em-euro (first transacoes)) ; {:valor 9.240M, :tipo "despesa", :comentatio "Almoço", :moeda "?", :data "19/11/2016"}

(transacao-em-yuan (first transacoes)) ; {:valor 70.950M, :tipo "despesa", :comentatio "Almoço", :moeda "¥", :data "19/11/2016"}