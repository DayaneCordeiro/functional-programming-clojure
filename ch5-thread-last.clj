;; ->> thread last é outra macro, mas ela passa o resultado da aplicação de um função como último
;; argumento da função seguinte
(->> (filter despesa? transacoes)
     (map valor)
     (reduce +)) ; 62.0