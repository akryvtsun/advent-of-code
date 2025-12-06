package year_2025

import UNKNOWN_VALUE

class Day06(val input: String) {

    fun solvePart1(): Long {
        var sum = 0L
        var data = input.lines()
        while (true) {
            val task = data.map { it.trimStart().substringBefore(" ") }
            data = data.map { (it.trimStart() + " ").substringAfter(" ") }
            val result = if (task.last() == "+")
                task.dropLast(1).sumOf { it.toLong() }
            else
                task.dropLast(1).map(String::toLong).reduce { a, b -> a * b }
            sum += result
            if (data.all { it.isBlank() }) break
        }
        return sum
    }

    fun solvePart2(): Int {
        return UNKNOWN_VALUE
    }
}