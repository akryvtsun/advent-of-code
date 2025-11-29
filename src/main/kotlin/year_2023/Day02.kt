package year_2023

class Day02(private val input: List<String>) {

    fun solvePart1(): Int {
        return input
            .map { line ->
                val first = line.first { it.isDigit() }
                val last = line.findLast { it.isDigit() }
                "$first$last"
            }
            .sumOf { it.toInt() }
    }
}