package com.bignerdranch.nyethack

import kotlin.system.exitProcess

fun main(args: Array<String>) {
    Game.play()
}

object Game {

    private val player = Player("Madrigal")
    private var currentRoom: Room = TownSquare()

    private var worldMap = listOf(
        listOf(currentRoom, Room("Tavern"), Room("Back Room")),
        listOf(Room("Long Corridor"), Room("Generic Room"))
    )

    init {
        println("Welcome, adventurer.\n")
        player.castFireBall()
    }

    fun play() {
        while (true) {
            println(currentRoom.description())
            println(currentRoom.load())

            printPlayerStatus(player)

            print("> Enter your command: ")
            val input = readLine()
            if (input.equals("quit")) {
                println("Quit from game")
                break
            } else
                println(GameInput(input).processCommand())

        }
    }

    private fun printPlayerStatus(player: Player) {
        println(
            "(Aura: ${player.auraColor()}) " +
                    "(Blessed: ${if (player.isBlessed) "Yes" else "No"})"
        )
        println("${player.name} ${player.formatHealthStatus()}")
    }

    private fun move(directionInput: String) =
        try {
            val direction = Direction.valueOf(directionInput.toUpperCase())
            val newPosition = direction.updateCoordinate(player.currentPosition)
            if (!newPosition.isInBounds) {
                throw IllegalStateException("$direction is out of bounds.")
            }
            val newRoom = worldMap[newPosition.y][newPosition.x]
            player.currentPosition = newPosition
            currentRoom = newRoom
            "OK, you move $direction to the ${newRoom.name}.\n${newRoom.load()}"
        } catch (e: Exception) {
            "Invalid direction: $directionInput."
        }

    private fun fight() = currentRoom.monster?.let {
        while (player.healthPoints > 0 && it.healthPoints > 0){
            slay(it)
            Thread.sleep(1000)
        }
        "Combat complete."
    } ?: "There's nothing here to fight."

    private fun slay(monster: Monster) {
        println("${monster.name} did ${monster.attack(player)} damage!")
        println("${player.name} did ${player.attack(monster)} damage!")
        if (player.healthPoints <= 0) {
            println(">>>> You have been defeated! Thanks for playing. <<<<")
            exitProcess(0)
        }
        if (monster.healthPoints <= 0) {
            println(">>>> ${monster.name} has been defeated! <<<<")
            currentRoom.monster = null
        }
    }

    private class GameInput(arg: String?) {
        private val input = arg ?: ""

        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1) { "" }

        fun processCommand() = when (command.toLowerCase()) {
            "move" -> move(argument)
            "fight" -> fight()
            "map" -> getMap(worldMap, currentRoom)
            "ring" -> ringBell(currentRoom, argument)
            else -> commandNotFound()
        }

        private fun ringBell(currentRoom: Room, countString: String) =
            try {
                val count = if (countString.isEmpty())
                    1
                else
                    countString.toInt()

                if (currentRoom is TownSquare) {
                    var result = ""
                    for (i in 1..count)
                        result += "${currentRoom.ringBell()}\n"
                    result
                } else
                    "You must be in town square, if want to ring the bell!"

            } catch (e: Exception) {
                "Invalid number $countString"
            }


        private fun commandNotFound() = "I'm not quite sure what you're trying to do!"
    }

    private fun getMap(worldMap: List<List<Room>>, currentRoom: Room): String {
        var mapInfo = ""
        for (roomList in worldMap) {
            for (room in roomList) {
                mapInfo += " " + if (room == currentRoom)
                    "X"
                else
                    "O"
            }
            mapInfo += "\n"
        }
        return mapInfo
    }
}