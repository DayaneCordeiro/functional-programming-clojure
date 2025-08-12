;; as funções em clojure devem ser lidas de dentro para fora para facilitar o entendimento.
;; existe uma macro -> que significa thread fisrt ou seja o resultado de uma linha é usado como o
;; primeiro argumento da função seguinte

;; função normal
(valor (first transacoes))

;; thread first:
(-> (first transacoes)
    (valor)) ; 33.0