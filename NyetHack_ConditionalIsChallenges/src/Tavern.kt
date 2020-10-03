import java.io.File
import kotlin.math.roundToInt

const val TAVERN_NAME = "Taernyl's Folly"

var playerGold = 10
var playerSilver = 10

//val patronList: List<String> = listOf("Eli", "Mordoc", "Sophie")
val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniquePatrons = mutableSetOf<String>()

//val readOnlyPatronList = patronList.toList()
val menuList = File("data/tavern-menu-items.txt")
    .readText()
    .split("\n")

fun main() {
    printMenu(menuList)
}

fun performPurchase(price: Double) {
    displayBalance()
    val totalPurse = playerGold + (playerSilver / 100.0)
    println("Total purse: $totalPurse")
    println("Purchase item for $price")

    val remainingBalance = totalPurse - price
    if (remainingBalance < 0) {
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

private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.replace(", ", ",").split(",")
    val message = "$patronName buys a $name ($type) for $price."
    println(message)

//    performPurchase(price.toDouble() ?: 0.0)

    val phrase = if (name == "Dragon's Breath") {
        "$patronName exclaims: ${toDragonSpeak("Ah, delicious $name!")}"
    } else {
        "$patronName exclaims: Thanks for the $name!"
    }
    println(phrase)
}

private fun printMenu(menuList: List<String>) {
    var pointMinimum = 12 // Points in menu item
    var maxLen = 0
    menuList.forEach {
        val (_, name, price) = it.split(",")
        val textLength = "$name$price".length
        if (textLength > maxLen) {
            maxLen = textLength
        }
    }
    maxLen += pointMinimum
    println(maxLen)
    val text = "Welcome to $TAVERN_NAME"
    println("${"*".repeat((maxLen - text.length) / 2 -1)} $text ${"*".repeat((maxLen - text.length) / 2 - 1)}")
    var lastMeal = ""
    val sortedMenuList = menuList.sortedBy {
        it.split(",")[0]
    }
    sortedMenuList.forEach {
        val (type, name, price) = it.split(",")
        val pointCount = maxLen - name.length - price.length
        if (!lastMeal.equals(type)){
            val text = "~[$type]~"
            println("${" ".repeat((maxLen - text.length) / 2)}$text")
            lastMeal = type
        }
        println("$name${".".repeat(pointCount)}$price")

    }
}