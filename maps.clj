;; Em Clojure, mapas são utilizados para representar entidades (invés de modelagem de classes)
;; as keywords são usadas como chave de mapas
;; vírgulas são opcionais, mas o repl normalmente imprime com vírgula

;; Um mapa pode ser criado assim:
(hash-map :valor 200, :tipo "receita") ; {:valor 200, :tipo "receita"}

;; ou assim
(def transacao {:valor 200 :tipo "receita"}) ; #'user/transacao

;; adicionado mais um par chave-valor ao mapa:
(assoc transacao :categoria "Educação") ; {:valor 200, :tipo "receita", :categoria "Educação"}

;; mas pela imutabilidade, o mapa original é mantido intacto
transacao ; {:valor 200, :tipo "receita"}

;; para acessar um elemento do mapa:
(get transacao :valor) ; 200

;; ou assim:
(:valor transacao)

;; chaves de mapas não precisam necessariamente ser keywords, mas as keywords facilitam as buscas no
;; mapa como mostrado acima, pois elas podem ser usadas como funções para pegar os valores.
{"chave" "valor"} ; {"chave" "valor"}

;; é possível incluir valores opcionais quando chaves não são encontradas: ===============
(def transacao-desnecessaria {:valor 34
                              :tipo "despesa"
                              :rotulos '("desnecessaria"
                                         "cartao")}) ; #'user/transacao-desnecessaria

(:rotulos transacao-desnecessaria) ; ("desnecessaria" "cartao")

(:rotulos transacao) ; nil

;; vai retornar uma lista vazia caso a chave :rotulos não seja encontrada:
(:rotulos transacao '()) ; ()
