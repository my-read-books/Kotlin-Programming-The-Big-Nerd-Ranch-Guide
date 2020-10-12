package chapter_19_Functional_programming

// map  flatMap
// map  <T, R> Iterable<T>.map(transform: (T) -> R): List<R>

fun main() {
    animalsBabies()
    tenDollarWords()

    flatMapTest()
}

private fun animalsBabies() {
    val animals = listOf("zebra", "giraffe", "elephant", "rat")
    val babies = animals
        .map { animal -> "A baby $animal" }
        .map { baby -> "$baby, with the cutest little tail ever!" }
    println(babies)
    println(animals)
}

private fun tenDollarWords() {
    val tenDollarWords = listOf("auspicious", "avuncular", "obviate")
    val tenDollarWordLengths = tenDollarWords.map { it.length }
    println(tenDollarWordLengths)
}

private fun flatMapTest(){
    println(listOf(listOf(1, 2, 3), listOf(4, 5, 6)).flatMap { it })
}