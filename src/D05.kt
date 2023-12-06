package aoc

private typealias AMap = List<Pair<LongRange, Long>>


private data class Almanac(
    val seeds: List<Long>,
    val maps: List<AMap>,
) {
    fun getLocation(seed: Long): Long {
        return maps.fold(seed) {acc, map ->
            val mapEntry = map.find { it.first.contains(acc) }
            mapEntry?.let { it.second + acc } ?: acc
        }
    }

    fun getLocations(): List<Long> {
        return seeds.map { getLocation(it) }
    }

    companion object
}

private fun parseMap(entries: List<String>): AMap {
    return entries.subList(1, entries.size).map {
        val (dest, src, size) = it.split(" ").map(String::toLong)
        src..src+size to dest - src
    }
}

private fun parseMapEntries(input: List<String>, maps: MutableList<AMap>) {
    val oneMap = input.takeWhile { it.isNotBlank() }
    maps.add(parseMap(oneMap))
    if(oneMap.size == input.size) return
    parseMapEntries(input.subList(oneMap.size + 1, input.size), maps)
}

private operator fun Almanac.Companion.invoke(input: List<String>): Almanac {
    val seeds = input.first().substringAfter("seeds: ").split(" ").map(String::toLong)
    val mapEntries = mutableListOf<AMap>()
    parseMapEntries(input.subList(2, input.size), mapEntries)
    return Almanac(seeds, mapEntries)
}
data object D05 : Day(5) {
    override fun a(): String {
        val almanac = Almanac(puzzleInput)
        val result = almanac.getLocations().min()
        return "$result"
    }

    override fun b(): String {
        return "TODO"
    }
}