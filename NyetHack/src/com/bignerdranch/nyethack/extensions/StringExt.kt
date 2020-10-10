package com.bignerdranch.nyethack.extensions

fun String.frame(padding: Int, formatChar: String = "*"): String {
    val greeting = "$this!"
    val middle = formatChar.padEnd(padding)
        .plus(greeting)
        .plus(formatChar.padStart(padding))
    val end = (0 until middle.length).joinToString("") { formatChar }
    return "$end\n$middle\n$end"
}

fun main() {
    println("Hello, World".frame(5))
}