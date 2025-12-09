package year_2025

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

data class Point2d(val x: Int, val y: Int)

fun Pair<Point2d, Point2d>.square(): Long =
    (abs(first.x - second.x) + 1L) * (abs(first.y - second.y) + 1)

class Day09(input: String) {

    val points = input.lines()
        .map {
            it
                .split(",")
                .map(String::toInt)
                .let { (x, y) -> Point2d(x, y) }
        }

    fun solvePart1(): Long {
        return points.pairs().maxOf { it.square() }
    }

    data class Line(val a: Point2d, val b: Point2d)

    fun solvePart2(): Long {

        fun distance(p1: Point2d, p2: Point2d) =
            (abs(p1.x - p2.x) + 1) + (abs(p1.y - p2.y) + 1)

        fun buildHull(points: List<Point2d>): List<Line> {
            var candid = points.first()
            val hull = mutableListOf(candid)
            var tail = points.drop(1).toMutableList()

            while (tail.isNotEmpty()) {
                val next = tail
                    .filter { candid.x == it.x || candid.y == it.y }
                    .minWith { p1, p2 -> distance(p1, p2) }
                hull += next
                candid = next
                tail -= candid
            }

            return (hull + hull.first()).zipWithNext { a, b -> Line(a, b) }
        }

        val hull: List<Line> = buildHull(points)

        fun isInHull(p: Point2d): Boolean {
            val c =
                hull
                    .filter { it.a.x == it.b.x }    // remove all horizontal lines
                    .filter { p.x <= it.a.x }       // take only vertical lines from the right
                    .count {
                        p.y in min(it.a.y, it.b.y)..max(it.a.y, it.b.y)
                    }
            return c % 2 != 0
        }

        return points.pairs()
            .filter {
                val point3 = Point2d(it.second.x, it.first.y)
                val point4 = Point2d(it.first.x, it.second.y)
                isInHull(point3) && isInHull(point4)
            }
            .maxOf { it.square() }
    }
}