(ns maps)

{:nome "clojure" :tipo "lisp"}
(hash-map :nome "clojure" :tipo "lisp")

(def linguagem {:nome "clojure" :tipo "lisp"})

;; adicionando mais um par de chaves
(assoc linguagem :categoria "programação")
;; {:nome "clojure", :tipo "lisp", :categoria "programação"}

;; pegando elementos do mapa
(get linguagem :nome) ;; "clojure"

;; removendo chave do mapa
(dissoc linguagem :tipo) ;; {:nome "clojure"}

;; update no valor de alguma chave - apesar de chamar update, o map continua
;; imutável
(update linguagem :nome (fn [v] "Java"))
;; {:nome "Java", :tipo "lisp"}


;; ---- exemplos do livro ----
(def transacao {:valor 200 :tipo "receita"})

;; adicionando chaves
(assoc transacao :categoria "Educação")

;; é possível adicionar valores opcionais caso uma chave não seja encontrada
;; no mapa
(def transacao-desnecessaria {:valor 34
                              :tipo "despesa"
                              :rotulos '("desnecessária"
                                          "cartão")})

(:rotulos	transacao-desnecessaria)
;;	("desnecessária"	"cartão")

(:rotulos	transacao)
;;	nil

;; se não existir vai retornar uma lista vazia - retornar nil pode gerar
;; uma série de problemas
(:rotulos	transacao	'())
;;	()

(:rotulos transacao-desnecessaria '())
;; ("desnecessária" "cartão")