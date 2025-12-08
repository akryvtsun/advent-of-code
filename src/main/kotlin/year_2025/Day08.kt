package year_2025

import UNKNOWN_VALUE

class Day08(input: String) {

    data class Point3d(val x: Int, val y: Int, val z: Int)

    val boxes = input.lines()
        .map {
            val (x, y, z) = it.split(",").map(String::toInt)
            Point3d(x, y, z)
        }

    fun solvePart1(): Int {
        val circuits = boxes.map { mutableListOf(it) }
        val max3circuits = circuits.sortedBy { it.size }.take(3)
        return max3circuits.fold(1) { acc, i -> acc * i.size }
    }

    fun solvePart2(): Int {
        return UNKNOWN_VALUE
    }
}