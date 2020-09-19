fun main() {
    runSimulation()

}

inline fun runSimulation() {
    val greetingFunction = configureGreetingFunction()
    println(greetingFunction("Guyal"))
    println(greetingFunction("Guyal"))
}

fun configureGreetingFunction(): (String) -> String{
    val structureType = "hospitals"
    var numBuildings = 5
    return {playerName: String ->
        val currentYear = 2020
        numBuildings += 1
        println("Adding $numBuildings $structureType")
        "Welcome to SimVillage, $playerName! (copyright $currentYear)"
    }
}

fun printConstructionCost(numBuildings: Int) {
    val cost = 500
    println("construction cost: ${cost * numBuildings}")
}