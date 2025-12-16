package year_2023

import AocDay

class Day01(input: String): AocDay<Int, Int>(input) {

    val digiMap = mapOf(
        "zero" to 0,
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
    )

    override fun solvePart1(): Int {
        return input.lines()
            .map { line ->
                val first = line.first { it.isDigit() }
                val last = line.findLast { it.isDigit() }
                "$first$last"
            }
            .sumOf { it.toInt() }
    }

    override fun solvePart2(): Int {
        return input.lines()
            .sumOf { line ->
                val nums = numbers(line)
                val first = nums.first()
                val last = nums.last()
                first * 10 + last
            }
    }

    private fun numbers(line: String) = buildList {
        for (i in line.indices) {
            if (line[i].isDigit()) {
                add(line[i].digitToInt())
            } else {
                for (e in digiMap.entries) {
                    if (line.startsWith(e.key, i)) {
                        add(e.value)
                        break
                    }
                }
            }
        }
    }
}