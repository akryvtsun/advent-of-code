package year_2025

import kotlin.math.pow
import kotlin.math.sqrt

class Day08(input: String) {

    data class Point3d(val x: Int, val y: Int, val z: Int) {
        // not Manhattan distance!
        fun distanceTo(other: Point3d): Double =
            sqrt(
                (x - other.x).toDouble().pow(2) +
                        (y - other.y).toDouble().pow(2) +
                        (z - other.z).toDouble().pow(2)
            )
    }

    val boxes = input.lines()
        .map {
            val (x, y, z) = it.split(",").map(String::toInt)
            Point3d(x, y, z)
        }

    fun solvePart1(pairs: Int): Int {
        // generate boxes pairs sorted by distance
        val dists =
            permutations(boxes)
                .sortedBy { (b1, b2) -> b1.distanceTo(b2) }

        // merge pairs into circuit sets
        val shortest10dists = dists.take(pairs)
        val init = boxes.map { setOf(it) }.toMutableList()
        val circuits = shortest10dists
            .fold(init) { acc, pair ->
                val addSet = mutableSetOf<Point3d>()

                val first = acc.firstOrNull { pair.first in it }
                first?.let {
                    acc.remove(it)
                    addSet.addAll(it)
                }

                val second = acc.firstOrNull { pair.second in it }
                second?.let {
                    acc.remove(it)
                    addSet.addAll(it)
                }

                if (addSet.isNotEmpty()) acc.add(addSet)

                acc
            }

        val max3circuits = circuits.sortedByDescending { it.size }.take(3)
        return max3circuits.fold(1) { acc, i -> acc * i.size }
    }

    fun solvePart2(): Long {
        // generate boxes pairs sorted by distance
        val dists =
            permutations(boxes)
                .sortedBy { (b1, b2) -> b1.distanceTo(b2) }

        // merge pairs into circuit sets
        val init = boxes.map { setOf(it) }.toMutableList()
        var res: Long? = null
        val circuits = dists
            .fold(init) { acc, pair ->
                val addSet = mutableSetOf<Point3d>()

                val first = acc.firstOrNull { pair.first in it }
                first?.let {
                    acc.remove(it)
                    addSet.addAll(it)
                }

                val second = acc.firstOrNull { pair.second in it }
                second?.let {
                    acc.remove(it)
                    addSet.addAll(it)
                }

                if (addSet.isNotEmpty()) acc.add(addSet)

                if (acc.size == 1 && res == null) {
                    res = pair.first.x.toLong() * pair.second.x.toLong()
                }

                acc
            }
        return res!!
    }
}