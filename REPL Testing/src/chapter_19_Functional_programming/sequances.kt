package chapter_19_Functional_programming

fun main() {
    val oneThousandPrimes = generateSequence(3) { value ->
        value + 1
    }.filter { it.isPrime() }.take(1000)
    print(oneThousandPrimes.iterator().next())
}

fun Int.isPrime(): Boolean {
    (2 until this).map {
        if (this % it == 0) {
            return false // Не простое!
        }
    }
    return true
}