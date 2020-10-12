package chapter_19_Functional_programming

fun main() {
filterExample()
    primeNumbers()
}

private fun filterExample(){
    val itemsOfManyColors = listOf(listOf("red apple", "green apple", "blue apple"),
        listOf("red fish", "blue fish"), listOf("yellow banana", "teal banana"))
    val redItems = itemsOfManyColors.flatMap { it.filter { it.contains("red") } }
    print(redItems)
}

private fun primeNumbers(){
    val numbers = listOf(7, 4, 8, 4, 3, 22, 18, 11)
    val primes = numbers.filter { number ->
        (2 until number).map { number % it }
            .none { it == 0 }
    }
    print(primes)
}