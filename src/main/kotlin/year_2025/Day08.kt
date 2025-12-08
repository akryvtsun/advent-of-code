package year_2025

import UNKNOWN_VALUE
import kotlin.math.pow
import kotlin.math.sqrt

data class Point3d(val x: Int, val y: Int, val z: Int) {
    // not Manhattan distance!
    fun distanceTo(other: Point3d): Double =
        sqrt(
            (x - other.x).toDouble().pow(2) +
                    (y - other.y).toDouble().pow(2) +
                    (z - other.z).toDouble().pow(2)
        )
}

typealias Circuit = Set<Point3d>

class Day08(input: String) {

    val boxes = input.lines()
        .map {
            val (x, y, z) = it.split(",").map(String::toInt)
            Point3d(x, y, z)
        }

    fun permutations(elements: List<Point3d>): List<Pair<Point3d, Point3d>> {
        if (elements.size == 1) return emptyList()
        val head = elements.first()
        val tail = elements.drop(1)
        return tail.map { head to it } + permutations(tail)
    }

    fun solvePart1(): Int {
        // generate boxes pairs sorted by distance
        val dists =
            permutations(boxes)
            .sortedBy { (b1, b2) -> b1.distanceTo(b2) }

        // merge pairs into circuit sets
        val shortest10dists = dists.take(10)
        val circuits = shortest10dists
            .fold(mutableListOf<Circuit>()) { acc, pair ->
                val c = acc.firstOrNull { pair.first in it || pair.second in it }
                if (c == null) {
                    acc.add(setOf(pair.first, pair.second))
                }
                else {
                    acc.remove(c)
                    acc.add(c + setOf(pair.first, pair.second))
                }
                acc
            }

        val max3circuits = circuits.sortedBy { it.size }.take(3)
        return max3circuits.fold(1) { acc, i -> acc * i.size }
    }

    fun solvePart2(): Int {
        return UNKNOWN_VALUE
    }
}