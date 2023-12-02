package aoc

private enum class Color(val str: String) {
    Red("red"),
    Green("green"),
    Blue("blue");

    companion object {
        fun fromString(string: String): Color {
            return enumValues<Color>().find { it.str == string } ?: throw IllegalArgumentException()
        }
    }
}

private data class ColorValue(val color: Color, val value: Int) {
    companion object
}

private operator fun ColorValue.Companion.invoke(input: String): ColorValue {
    val (value, color) = input.split(" ")
    return ColorValue(Color.fromString(color), value.toInt())
}

private data class Game(val number: Int, val red: Int, val green: Int, val blue: Int) {
    fun isPossible(actualRed: Int, actualGreen: Int, actualBlue: Int): Boolean {
        return red <= actualRed && green <= actualGreen && blue <= actualBlue
    }

    fun power(): Int = red * green * blue

    companion object
}

private operator fun Game.Companion.invoke(input: String): Game {
    val (gamePart, samplesPart) = input.split(": ")
    val gameNumber = gamePart.substring(5).toInt()
    val colorvalues = samplesPart
        .split("; ")
        .flatMap { it.split(", ") }
        .map { ColorValue(it) }
        .groupBy { it.color }
    return Game(
        gameNumber,
        colorvalues[Color.Red]?.maxOf { it.value } ?: 0,
        colorvalues[Color.Green]?.maxOf { it.value } ?: 0,
        colorvalues[Color.Blue]?.maxOf { it.value } ?: 0,
    )
}

data object D02 : Day(2) {
    override fun a(): String {
        val games = puzzleInput.map { Game(it) }
        val possibleGames = games.filter { it.isPossible(12,13,14) }
        val result = possibleGames.sumOf { it.number }
        return "$result"
    }

    override fun b(): String {
        val games = puzzleInput.map { Game(it) }
        val result = games.sumOf { it.power() }
        return "$result"
    }
}