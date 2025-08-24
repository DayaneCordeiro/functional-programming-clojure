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