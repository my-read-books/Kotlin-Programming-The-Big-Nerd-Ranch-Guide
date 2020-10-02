import kotlin.math.roundToInt

const val TAVERN_NAME = "Taernyl's Folly"

var playerGold = 10
var playerSilver = 10

fun main() {
    placeOrder("shandy, Dragon's Breath, 5.91")
}

fun performPurchase(price: Double){
    displayBalance()
    val totalPurse = playerGold + (playerSilver / 100.0)
    println("Total purse: $totalPurse")
    println("Purchase item for $price")

    val remainingBalance = totalPurse - price
    if (remainingBalance < 0){
        println("Not enough money to buy item")
        return
    }
    println("Remaining balance: ${"%.2f".format(remainingBalance)}")

    val remainingGold = remainingBalance.toInt()
    val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
    playerGold = remainingGold
    playerSilver = remainingSilver
    displayBalance()
}

fun displayBalance() {
    println("Player's purse balance: Gold: $playerGold, Silver: $playerSilver")
}

private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[aeiouAEIOU]")) {
        when (it.value.toLowerCase()) {
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }

private fun placeOrder(menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("Madrigal speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.replace(", ", ",").split(",")
    val message = "Madrigal buys a $name ($type) for $price."
    println(message)

    performPurchase(price.toDouble() ?: 0.0)

    val phrase = if (name == "Dragon's Breath") {
        "Madrigal exclaims: ${toDragonSpeak("Ah, delicious $name!")}"
    } else {
        "Madrigal exclaims: Thanks for the $name!"
    }
    println(phrase)
}