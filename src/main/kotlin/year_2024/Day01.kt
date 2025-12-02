package year_2024

import kotlin.math.abs

class Day01(private val input: String) {

    private fun transform(input: String): Pair<List<Long>, List<Long>> {
        val left = mutableListOf<Long>()
        val right = mutableListOf<Long>()
        input.lines().forEach { line ->
            val parts = line.split("   ")
            left.add(parts[0].toLong())
            right.add(parts[1].toLong())
        }
        return left to right
    }

    fun solvePart1(): Long {
        val (list1, list2) = transform(input)
        val sorted1 = list1.sorted()
        val sorted2 = list2.sorted()
        val pairs = sorted1 zip sorted2
        return pairs.map { abs(it.second - it.first) }.sum()
    }

    fun solvePart2(): Long {
        val (list1, list2) = transform(input)
        return list1
            .map { target -> target * list2.count { it == target } }
            .sum()
    }
}