fun main(args: Array<String>) {
    val name = "Madrigal"
    var healthPoints = 50
    val isBlessed = true
    val isImmortal = true
    val statusFormatString = "(HP)(A) -> H"

    val auraVisible = isBlessed && healthPoints > 50 || isImmortal
    val auraColor = if (auraVisible) when ((Math.pow(Math.random(), (110 - healthPoints) / 100.0) * 20).toInt()){
        in 0..5 -> "red"
        in 6..10 -> "orange"
        in 11..15 -> "purple"
        in 16..20 -> "green"
        else -> "unknown"
    } else "NONE"

    val healthStatus = when (healthPoints){
        100 -> "is in excellent condition!"
        in 90..99 -> "has a few scratches."
        in 75..89 -> if (isBlessed) {
            "has some minor wounds but is healing quite quickly!"
        } else {
            "has some minor wounds."
        }
        in 15..74 -> "looks pretty hurt."
        else -> "is in awful condition!"
    }

    var statusString = statusFormatString.replace("B", "Blessed: ${if (isBlessed) "YES" else "NO" }")
        .replace("A", "Aura: $auraColor")
        .replace("HP", "HP: $healthPoints")
     statusString = Regex("H(?!P)").replace(statusString, "$name $healthStatus")
    println(statusString)
//    println("${if (isBlessed) "(Aura: $auraColor)" else ""}\n$name $healthStatus")
}