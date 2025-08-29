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