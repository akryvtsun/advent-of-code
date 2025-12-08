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

    // generate boxes pairs sorted by distance
    val distances = boxes.pairs()
            .sortedBy { (b1, b2) -> b1.distanceTo(b2) }

    private fun mergeCircuits(distances: Sequence<Pair<Point3d, Point3d>>): Pair<List<Set<Point3d>>, Pair<Point3d, Point3d>?> {
        val circuits = boxes.map { setOf(it) }.toMutableList()
        var lastPair: Pair<Point3d, Point3d>? = null
        for (pair in distances) {
            val mergedCircuit = mutableSetOf<Point3d>()

            fun process(box: Point3d) {
                val oldCircuit = circuits.firstOrNull { box in it }
                oldCircuit?.let {
                    circuits.remove(it)
                    mergedCircuit.addAll(it)
                }
            }

            process(pair.first)
            process(pair.second)

            if (mergedCircuit.isNotEmpty()) circuits.add(mergedCircuit)

            if (circuits.size == 1 && lastPair == null) {
                lastPair = pair
            }
        }
        return circuits to lastPair
    }

    fun solvePart1(pairsLimit: Int): Int {
        val limited = distances.take(pairsLimit)

        val (circuits, _) = mergeCircuits(limited)

        val max3circuits = circuits.sortedByDescending { it.size }.take(3)
        return max3circuits.fold(1) { acc, i -> acc * i.size }
    }

    fun solvePart2(): Long {
        val (_, lastPair) = mergeCircuits(distances)
        return lastPair!!.first.x.toLong() * lastPair.second.x.toLong()
    }
}