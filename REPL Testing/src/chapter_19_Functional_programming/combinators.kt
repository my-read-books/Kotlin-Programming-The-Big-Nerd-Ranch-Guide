package chapter_19_Functional_programming

// zip fold

fun main() {
    zipExample()
    foldExample()
}

private fun zipExample(){
    val numbers = listOf(7, 4, 8, 4, 3, 22, 18, 11)
    val primes = numbers.filter { number ->
        (2 until number).map { number % it }
            .none { it == 0 }
    }
    println(primes)
}

private fun foldExample(){
    val foldedValue = listOf(1, 2, 3, 4).fold(0) { accumulator, number ->
        println("Accumulated value: $accumulator")
        accumulator + (number * 3)
    }
    println("Final value: $foldedValue")
}