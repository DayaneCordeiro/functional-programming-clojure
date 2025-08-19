;; composição de funções = combinar várias funções em uma de forma que a aplicação de uma só função
;; corresponsa à aplicação de várias.
;; Clojure tem um recurso comp que permite compor funções

;; composição de data-transacao e transacao-em-yuan em texto-resumo-em-yuan
;; usa-se def e não defn, pois comp funciona como fn
;; ele primeiro passa o argumento para a ultima função (transacao-em-yuan) e depois para data-transacao
(def texto-resumo-em-yuan (comp data-transacao transacao-em-yuan)) ; #'user/texto-resumo-em-yuan

(map texto-resumo-em-yuan transacoes) ; ("19/11/2016 => ¥ -70.950" "01/12/2016 => ¥ +5805.000" "03/12/2016 => ¥ -62.350")



