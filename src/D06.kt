package aoc

import kotlin.math.pow
import kotlin.math.roundToLong
import kotlin.math.sqrt

private fun getSolutions(time: Long, distance: Long): LongRange {
    val (start, end) = pqFormula(time, distance)
    val firstWinning = -(start - 0.500001).roundToLong()
    val lastWinning = -(end + 0.500001).roundToLong()
    return firstWinning..lastWinning
}

private fun pqFormula(p: Long, q: Long): Pair<Double, Double> {
    val part1 = -(p / 2.0)
    val part2 = sqrt((p / 2.0).pow(2) - q)
    return part1 + part2 to part1 - part2
}

data object D06 : Day(6) {
    override fun a(): String {
        val (time, distance) = puzzleInput
        val times = time.split(Regex("\\s+")).mapIndexedNotNull { index, s -> if (index > 0) s.toLong() else null }
        val distances =
            distance.split(Regex("\\s+")).mapIndexedNotNull { index, s -> if (index > 0) s.toLong() else null }
        val data = times.zip(distances)
        val ranges = data.map { getSolutions(it.first, it.second) }
        val validNumbers = ranges.map { 1 + it.last - it.first }
        val result = validNumbers.reduce { acc, i -> acc * i }

        return "$result"
    }

    override fun b(): String {
        val (timeRow, distanceRow) = puzzleInput
        val time = timeRow.split(":")[1].replace(" ", "").toLong()
        val distance = distanceRow.split(":")[1].replace(" ", "").toLong()
        val range = getSolutions(time, distance)
        val validNumbers = 1 + range.last - range.first

        return "$validNumbers"
    }

}