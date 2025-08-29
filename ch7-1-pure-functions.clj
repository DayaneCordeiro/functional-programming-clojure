;; coisas que uma função pura não faz -> efeitos colaterais:
;;   Alterar valores dentro de um mapa
;;   Alterar uma string
;;   Escrever em um arquivo
;;   Imprimir na tela
;;   Inserir dados em um banco

(def de-para [{:de "a" :para "4"}
              {:de "e" :para "3"}
              {:de "i" :para "1"}
              {:de "o" :para "0"}])

;; função recursiva pura
(defn escrita-hacker [texto dicionario]
  (if (empty? dicionario)
    texto
    (let [conversao (first dicionario)]
      (escrita-hacker (clojure.string/replace texto
                                              (:de conversao)
                                              (:para conversao))
                      (rest dicionario)))))

(escrita-hacker "alameda" de-para) ; "4l4m3d4"

;; anteriormente, foi definida a função transacao-em-outra-moeda que era impura pois usava cotacoes
;; que era definido fora da função, para corrigir isso, é possivel reescrever assim:
(def cotacoes
  {:yuan {:cotacao 2.15M :simbolo "¥"}
   :euro {:cotacao 0.28M :simbolo "€"}})

(defn transacao-em-outra-moeda [cotacoes moeda transacao]
  (let [{{cotacao :cotacao simbolo :simbolo}
         moeda} cotacoes]
    (assoc transacao :valor (* (:valor transacao))
           :moeda simbolo)))

;; O código acima quebraria o restante do código que chama essa fn pois precisa de mais args
;; usando fn parciais para resolver:
(defn transacao-convertida [cotacoes moeda transacao]
  (let [{{cotacao :cotacao simbolo :simbolo}
         moeda} cotacoes]
    (assoc transacao :valor (* (:valor transacao))
           :moeda simbolo)))

(def transacao-em-outra-moeda (partial transacao-convertida cotacoes))

;; outra forma de resolver é com o recurso de multi-aridade
;; a função se comporta de formas diferentes de acordo com a quantidade de argumentos que recebe
(defn transacao-em-outra-moeda 
  ([cotacoes moeda transacao]
   (let [{{cotacao :cotacao simbolo :simbolo} moeda} cotacoes]
     (assoc transacao :valor (* cotacao (:valor transacao))
            :moeda simbolo)))
  ([moeda transacao]
   (transacao-em-outra-moeda cotacoes moeda transacao)))