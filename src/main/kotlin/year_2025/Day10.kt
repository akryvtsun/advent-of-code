package year_2025

import UNKNOWN_VALUE
import java.util.BitSet

class Day10(input: String) {

    data class Machine(val lights: BitSet, val buttons: List<BitSet>/*, val joltage: List<Int>*/)

    private fun lights(data: String): BitSet {
        val result = BitSet()
        data.withIndex().filter { it.value == '#' }.forEach { result.set(it.index - 1) }
        return result
    }

    private fun buttons(data: List<String>) =
        data.map {
            val result = BitSet()
            it.trim('(', ')')
                .split(',')
                .map(String::toInt)
                .forEach { i -> result.set(i) }
            result
        }

    val factory = input.lines()
        .map { line ->
            val parts = line.split(" ")
            Machine(
                lights(parts.first()),
                buttons(parts.subList(1, parts.size - 1))
                // parts.last()
            )
        }

    fun findMinPresses(m: Machine) = 1

    fun solvePart1(): Int =
        factory.sumOf {
            findMinPresses(it)
        }

    fun solvePart2(): Int {
        return UNKNOWN_VALUE
    }
}