package aoc

private data class NumberSymbol(val x: IntRange, val y: Int, val number: Int) {
    val adjacentPositions by lazy {
        val extended = x.first - 1..x.last + 1
        buildSet {
            add(Coord(extended.first, y))
            add(Coord(extended.last, y))
            extended.forEach {
                add(Coord(it, y - 1))
                add(Coord(it, y + 1))
            }
        }
    }

    fun isValid(symbols: Set<Coord>): Boolean {
        return symbols.intersect(adjacentPositions).isNotEmpty()
    }

    fun isAdjacent(coord: Coord): Boolean {
        return adjacentPositions.contains(coord)
    }
}

private fun parseSymbols(input: List<String>): Set<Coord> {
    return buildSet {
        input.forEachIndexed { yCoord, s ->
            s.forEachIndexed { xCooord, c ->
                if (c != '.' && !c.isDigit()) {
                    add(Coord(xCooord, yCoord))
                }
            }
        }
    }
}

private fun parseNumbers(input: List<String>): List<NumberSymbol> {
    val regex = Regex("[0-9]+")
    return buildList {
        input.forEachIndexed { yCoord, row ->
            val matches = regex.findAll(row)
            matches.forEach {
                val number = it.value.toInt()
                val xCoords = it.range
                add(NumberSymbol(xCoords, yCoord, number))
            }
        }
    }
}

private fun parseGearSymbols(input: List<String>): Set<Coord> {
    return buildSet {
        input.forEachIndexed { yCoord, s ->
            s.forEachIndexed { xCooord, c ->
                if (c == '*') {
                    add(Coord(xCooord, yCoord))
                }
            }
        }
    }
}

data object D03 : Day(3) {
    override fun a(): String {
        val symbols = parseSymbols(puzzleInput)
        val numberCandidates = parseNumbers(puzzleInput)
        val numbers = numberCandidates.filter { it.isValid(symbols) }
        val result = numbers.sumOf { it.number }
        return "$result"
    }

    override fun b(): String {
        val numbers = parseNumbers(puzzleInput)
        val gearSymbols = parseGearSymbols(puzzleInput)
        val gears = gearSymbols.mapNotNull { coord ->
            val adjacentNumbers = numbers.filter { it.isAdjacent(coord) }
            if(adjacentNumbers.size == 2){
                val (first, second) = adjacentNumbers
                first.number * second.number
            }
            else null
        }
        val result = gears.sum()
        return "$result"
    }
}