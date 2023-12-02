package aoc

data object D01 : Day(1) {
    override fun a(): String {
        return puzzleInput
            .map { it.filter(Char::isDigit) }
            .map {
                buildString {
                    append(it.first())
                    append(it.last())
                }
            }
            .map(String::toInt)
            .sum()
            .toString()
    }

    override fun b(): String {
        return puzzleInput
            .map { filterDigits(it) }
            .map {
                buildString {
                    append(it.first())
                    append(it.last())
                }
            }
            .map(String::toInt)
            .sum()
            .toString()
    }

    private fun filterDigits(string: String): String {
        val result = string.mapIndexedNotNull { index, c ->
            when {
                c.isDigit() && c != '0' -> c
                string.startsWith("one", index) -> '1'
                string.startsWith("two", index) -> '2'
                string.startsWith("three", index) -> '3'
                string.startsWith("four", index) -> '4'
                string.startsWith("five", index) -> '5'
                string.startsWith("six", index) -> '6'
                string.startsWith("seven", index) -> '7'
                string.startsWith("eight", index) -> '8'
                string.startsWith("nine", index) -> '9'
                else -> null
            }
        }
        return result.joinToString()
    }
}