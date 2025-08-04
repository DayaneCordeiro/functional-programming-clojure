;; é uma lista de valores únicos
(hash-set "Chico César" "Renata Arruda") ; #{"Renata Arruda" "Chico César"}

#{"Chico César" "Renata Arruda"} ; #{"Renata Arruda" "Chico César"}

(def artistas #{"Chico César" "Renata Arruda"}) ; #'user/artistas

;; incluindo um artista novo na lista -> entra em uma posição imprevisível
(conj artistas "Jackson do Pandeiro") ; #{"Renata Arruda" "Jackson do Pandeiro" "Chico César"}

;; tentando incluir um que já existe no set: -> não duplica
(conj artistas "Chico César") ; #{"Renata Arruda" "Chico César"}