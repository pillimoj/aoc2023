package aoc

import kotlin.math.pow
import kotlin.math.roundToInt

private data class Card(val cardNumberIndex: Int, val numbers: List<Int>, val winningNumbers: List<Int>) {
    val wins by lazy {
        numbers.filter { winningNumbers.contains(it) }.size
    }

    fun getValue(): Int {
        return if(wins > 0) {
            2.0.pow(wins - 1).roundToInt()
        } else 0
    }

    companion object
}
private operator fun Card.Companion.invoke(input: String): Card {
    val (cardNumberStr, content) = input.split(": ")
    val cardNumber = cardNumberStr.substring(4).trim().toInt()
    val (numbersStr, winningNumbersStr) = content.split("|")
    val numbers = numbersStr.split(" ").mapNotNull { if(it.isNotEmpty()) it.toInt() else null }
    val winningNumbers = winningNumbersStr.split(" ").mapNotNull { if(it.isNotEmpty()) it.toInt() else null }
    return Card(cardNumber - 1, numbers, winningNumbers)
}


data object D04 : Day(4) {
    override fun a(): String {
        val cards = puzzleInput.map { Card(it) }
        val result = cards.sumOf { it.getValue() }
        return "$result"
    }

    override fun b(): String {
        // Traverse backwards
        val results = mutableListOf<Int>()
        val originalCards = puzzleInput.map { Card(it) }
        originalCards.reversed().forEachIndexed { originalIndex, card ->
            val copies = List<Int>(card.wins) { j -> originalIndex - j - 1}
            val numberOfTotalCards = 1 + copies.map {
                results[it]
            }.sum()
            results.add(numberOfTotalCards)
        }
        val result = results.sum()
        return "$result"
    }

}