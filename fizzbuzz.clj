; primeira versão so com cond
(defn fizzbuzz-v1 [number]
  (cond (and (= 0 (mod number 3)) (= 0 (mod number 5))) "fizzbuzz"
        (= 0 (mod number 3)) "fizz"
        (= 0 (mod number 5)) "buzz"
        :else number))

; usando funções separadas:
(defn is-divisible-by? [dividend divisor]
  (zero? (mod dividend divisor)))

(defn fizzbuzz [number]
  (cond (and (is-divisible-by? number 3) (is-divisible-by? number 5)) "fizzbuzz"
        (is-divisible-by? number 3) "fizz"
        (is-divisible-by? number 5) "buzz"
        :else number))