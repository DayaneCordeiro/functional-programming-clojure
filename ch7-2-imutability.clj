;; Vantagens:
;; Dada a invariância de dados, as informações so precisam ser validadas quando criadas.
;; Com mutabilidade, dois dados que são iguais agora podem não ser mais iguais no futuro.
;; Compartilhar dados que não mudam é mais fácil.

;; Países membros do mercosul:
(def membros-fundadores
  (list "Argentina" "Brasil" "Paraguai" "Uruguai"))

membros-fundadores ; ("Argentina" "Brasil" "Paraguai" "Uruguai")

;; Venezuela só entrou em 2012
(def membros-plenos
  (cons "Venezuela" membros-fundadores))

membros-plenos ; ("Venezuela" "Argentina" "Brasil" "Paraguai" "Uruguai")

;; removendo o primeiro elemento de membros plenos para comparar com a lista anterior
;; rest ignora o primeiro elemento da lista
(rest membros-plenos) ; ("Argentina" "Brasil" "Paraguai" "Uruguai")

;; identical? compara dois elementos para saber se é o mesmo objeto
(identical? (rest membros-plenos) membros-fundadores) ; true

;; a lista de transações atual:
;; transacoes é uma referência a uma lista de mapas
;; se for necessário adicionar um elemento é necessário criar uma nova referência, com os valores
;; novos e os anteriores.
(def transacoes
  [{:valor 33M :tipo "despesa" :comentatio "Almoço" :moeda "R$" :data "19/11/2016"}
   {:valor 2700M :tipo "receita" :comentatio "Bico" :moeda "R$" :data "01/12/2016"}
   {:valor 29M :tipo "despesa" :comentatio "Livro de Clojure" :moeda "R$" :data "03/12/2016"}])

;; nova referência:
(def transacoes
  [{:valor 33M :tipo "despesa" :comentatio "Almoço" :moeda "R$" :data "19/11/2016"}
   {:valor 2700M :tipo "receita" :comentatio "Bico" :moeda "R$" :data "01/12/2016"}
   {:valor 29M :tipo "despesa" :comentatio "Livro de Clojure" :moeda "R$" :data "03/12/2016"}
   {:valor 45M :tipo "despesa" :comentatio "Jogo na steam" :moeda "R$" :data "26/12/2016"}])

;; ou:
(def transacoes
  [{:valor 33M :tipo "despesa" :comentatio "Almoço" :moeda "R$" :data "19/11/2016"}
   {:valor 2700M :tipo "receita" :comentatio "Bico" :moeda "R$" :data "01/12/2016"}
   {:valor 29M :tipo "despesa" :comentatio "Livro de Clojure" :moeda "R$" :data "03/12/2016"}])

;; o valor dentro de transacoes não muda, mas a referência transacoes muda para outra coleção
(def transacoes (cons {:valor 29M :tipo "despesa" :comentatio "Livro de Clojure" 
                       :moeda "R$" :data "03/12/2016"}))

;; as vezes pode ser necessário manter a referência e mudar o valor do conteúdo como por exemplo um
;; contador ou caching. Clojure possiu o atom que é uma das formas que a linguagem provê
;; o gerenciamento de estado.

;; registros referencia um átomo que contem uma lista vazia
(def registros (atom ())) ; #'user/registros

registros ; #object[clojure.lang.Atom 0xdc411ca {:status :ready, :val ()}]

;; para obter o valor atual do estado do átomo usamos o @
@registros ; ()

;; para incluir novos elementos usamos a fn swap! a "!" no nome simboliza que uma mudança de 
;; estado vai acontecer. swap! precisa de dois parâmetros: o átomo alvo e uma função
;; junto de seus parâmetros que será aplicada aos valores que o átomo contém.

;; inserindo novos elementos no átomo:
(swap! registros conj {:valor 29M :tipo "despesa" :comentatio "Livro de Clojure"
                       :moeda "R$" :data "03/12/2016"})
; ({:valor 29M, :tipo "despesa", :comentatio "Livro de Clojure", :moeda "R$", :data "03/12/2016"})

(swap! registros conj {:valor 2700M :tipo "receita" :comentatio "Bico" :moeda "R$" :data "01/12/2016"})
; ({:valor 2700M, :tipo "receita", :comentatio "Bico", :moeda "R$", :data "01/12/2016"}
;  {:valor 29M, :tipo "despesa", :comentatio "Livro de Clojure", :moeda "R$", :data "03/12/2016"})

;; Para evitar duplicação
;; abstraindo a inclusão de dados no átomo
(defn registrar [transacao]
  (swap! registros conj transacao))

;; usando a abstração
(registrar {:valor 33M :tipo "despesa" :comentatio "Almoço" :moeda "R$" :data "19/11/2016"})
; ({:valor 2700M, :tipo "receita", :comentatio "Bico", :moeda "R$", :data "01/12/2016"} 
;  {:valor 29M, :tipo "despesa", :comentatio "Livro de Clojure", :moeda "R$", :data "03/12/2016"} 
;  {:valor 33M, :tipo "despesa", :comentatio "Almoço", :moeda "R$", :data "19/11/2016"})

;; usando a abstração novamente
(registrar {:valor 45M :tipo "despesa" :comentatio "Jogo na steam" :moeda "R$" :data "26/12/2016"})
; ({:valor 45M, :tipo "despesa", :comentatio "Jogo na steam", :moeda "R$", :data "26/12/2016"} 
;  {:valor 2700M, :tipo "receita", :comentatio "Bico", :moeda "R$", :data "01/12/2016"} 
;  {:valor 29M, :tipo "despesa", :comentatio "Livro de Clojure", :moeda "R$", :data "03/12/2016"} 
;  {:valor 33M, :tipo "despesa", :comentatio "Almoço", :moeda "R$", :data "19/11/2016"})

;; abstraindo a leitura de dados de um átomo
(def transacoes @registros)

transacoes
; ({:valor 45M, :tipo "despesa", :comentatio "Jogo na steam", :moeda "R$", :data "26/12/2016"} 
;  {:valor 2700M, :tipo "receita", :comentatio "Bico", :moeda "R$", :data "01/12/2016"} 
;  {:valor 29M, :tipo "despesa", :comentatio "Livro de Clojure", :moeda "R$", :data "03/12/2016"} 
;  {:valor 33M, :tipo "despesa", :comentatio "Almoço", :moeda "R$", :data "19/11/2016"})