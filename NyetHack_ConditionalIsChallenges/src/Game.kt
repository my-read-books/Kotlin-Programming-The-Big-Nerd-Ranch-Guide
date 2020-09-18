fun main(args: Array<String>) {
    val name = "Madrigal"
    var healthPoints = 50
    val isBlessed = true
    val isImmortal = true

    val auraColor = auraColor(isBlessed, healthPoints, isImmortal)

    val healthStatus = formatHealthStatus(healthPoints, isBlessed)

    printPlayerStatus(auraColor, isBlessed, name, healthStatus)
    castFireBall(5)
    castFireBall()
}

private fun printPlayerStatus(
    auraColor: String,
    isBlessed: Boolean,
    name: String,
    healthStatus: String
) {
    println(
        "(Aura: $auraColor) " +
                "(Blessed: ${if (isBlessed) "Yes" else "No"})"
    )
    println("$name $healthStatus")
}

private fun auraColor(isBlessed: Boolean, healthPoints: Int, isImmortal: Boolean) =
    if (isBlessed && healthPoints > 50 || isImmortal) "GREEN" else "NONE"


private fun formatHealthStatus(healthPoints: Int, isBlessed: Boolean) =
    when (healthPoints) {
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


private fun castFireBall(numFireBalls: Int = 2) =
    println("A glass of Fireball springs into existence (x$numFireBalls). Level of dope = ${
        dopeState((numFireBalls * 6.8).toInt())}")

private fun dopeState(dopeLevel:Int) = when(dopeLevel){
    in 1..10 -> "Tipsy"
    in 11..20 -> "Sloshed"
    in 21..30 -> "Soused"
    in 31..40 -> "Stewed"
    else -> "..t0aSt3d"
}