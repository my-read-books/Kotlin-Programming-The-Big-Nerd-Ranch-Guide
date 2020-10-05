package com.bignerdranch.nyethack

fun main(args: Array<String>) {

    val player = Player()
    player.castFireBall()


    printPlayerStatus(player)
}

private fun printPlayerStatus(player: Player) {
    println(
        "(Aura: ${player.auraColor()}) " +
                "(Blessed: ${if (player.isBlessed) "Yes" else "No"})"
    )
    println("${player.name} ${player.healthPoints}")
}


