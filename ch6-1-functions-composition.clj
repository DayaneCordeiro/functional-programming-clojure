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

;; e caso não exista uma moeda definida?
(defn valor-sinalizado [transacao]
  (let [moeda (:moeda transacao "R$") ;; ou pega a moeda de transação ou retorna R$
        valor (:valor transacao)]
    (if (= (:tipo transacao) "despesa")
      (str moeda " -" valor)
      (str moeda " +" valor)))) ; #'user/valor-sinalizado

(def transacao-aleatoria {:valor 9.0})

(valor-sinalizado transacao-aleatoria) ; "R$ +9.0"

;; agora incluimos a data nesse formato: "01/12/2016 => R$ +2700.00"
(defn data-transacao [transacao]
  (str (:data transacao) " => " (valor-sinalizado transacao))) ; #'user/data-transacao

(data-transacao (first transacoes)) ; "19/11/2016 => R$ -33.0"

;; e se quiser converter de moeda, BR para Chinesa?
;; dando exemplo de que R$1,00 valha ¥2,15
(defn transacao-em-yuan [transacao]
      (assoc transacao :valor (* 2.15 (:valor transacao)) ; assoc pega o mapa transacao e associa 
             :moeda "¥"))                                 ; os valores que seguem como argumento

(transacao-em-yuan (first transacoes)) ; {:valor 70.95, :tipo "despesa", :comentatio "Almoço", :moeda "¥", :data "19/11/2016"}

;; dando uma pequena melhorada:
(def cotacoes
  {:yuan {:cotacao 2.15 :simbolo "¥"}}) ; #'user/cotacoes

(defn transacao-em-yuan [transacao]
  (assoc transacao :valor (* (:cotacao (:yuan cotacoes))
                             (:valor transacao))
         :moeda (:simbolo (:yuan cotacoes)))) ; #'user/transacao-em-yuan

(transacao-em-yuan (first transacoes)) ; {:valor 70.95, :tipo "despesa", :comentatio "Almoço", :moeda "¥", :data "19/11/2016"}

;; so que com essa função resolve um problema e cria outro, ja que se torna mais complexo
;; ficar pegando a cotação, então da pra usar a função get-in
(defn transacao-em-yuan [transacao]
  (assoc transacao :valor (* (get-in cotacoes [:yuan :cotacao])
                             (:valor transacao))
         :moeda (get-in cotacoes [:yuan :simbolo]))) ; #'user/transacao-em-yuan

(transacao-em-yuan (first transacoes)) ; {:valor 70.95, :tipo "despesa", :comentatio "Almoço", :moeda "¥", :data "19/11/2016"}

;; ainda assim está mais complicado do que precisa ser então podemos usar o let
(defn transacao-em-yuan [transacao]
  (let [yuan (:yuan cotacoes)]
    (assoc transacao :valor (* (:cotacao yuan) (:valor transacao))
           :moeda (:simbolo yuan)))) ; #'user/transacao-em-yuan

(transacao-em-yuan (first transacoes)) ; {:valor 70.95, :tipo "despesa", :comentatio "Almoço", :moeda "¥", :data "19/11/2016"}

(data-transacao (first transacoes)) ; "19/11/2016 => R$ -33.0"

(data-transacao (transacao-em-yuan (first transacoes))) ; "19/11/2016 => ¥ -70.95"

(defn texto-resumo-em-yuan [transacao]
  (data-transacao (transacao-em-yuan transacao))) ; #'user/texto-resumo-em-yuan

(map texto-resumo-em-yuan transacoes) ; ("19/11/2016 => ¥ -70.95" "01/12/2016 => ¥ +5805.0" "03/12/2016 => ¥ -62.349999999999994")

;; nota-se que a operação acima retornou uma dizima periódica
;; para ter precisão é necessário utilizad o BigDecimal
(class 3.1) ; java.lang.Double

(* 3.1 3.1) ; 9.610000000000001

(class 3.1M) ; java.math.BigDecimal <======

(* 3.1M 3.1) ; 9.610000000000001

(* 3.1M 3.1M) ; 9.61M

(class (* 3.1M 3.1M)) ; java.math.BigDecimal

;; ====================================================================
(def cotacoes
  {:yuan {:cotacao 2.15M :simbolo "¥"}})

(def transacoes
  [{:valor 33.0M :tipo "despesa" :comentatio "Almoço" :moeda "R$" :data "19/11/2016"}
   {:valor 2700.0M :tipo "receita" :comentatio "Bico" :moeda "R$" :data "01/12/2016"}
   {:valor 29.0M :tipo "despesa" :comentatio "Livro de Clojure" :moeda "R$" :data "03/12/2016"}])

(map texto-resumo-em-yuan transacoes) ; ("19/11/2016 => ¥ -70.950" "01/12/2016 => ¥ +5805.000" "03/12/2016 => ¥ -62.350")

(defn texto-resumo-em-yuan [transacao]
  (data-transacao (transacao-em-yuan transacao)))

;; Essa função é uma boa candidata á utilização da macro thread-first pois ela aplica a função
;; data-transacao a cada valor retornado por transacao-em-yuan
(defn texto-resumo-em-yuan [transacao]
  (-> (transacao-em-yuan transacao)
      (data-transacao)))