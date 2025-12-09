package year_2025

import kotlin.math.abs

data class Point2d(val x: Int, val y: Int)

fun Pair<Point2d, Point2d>.square(): Long =
    (abs(first.x - second.x) + 1L) * (abs(first.y - second.y) + 1)

//fun main() {
//    val s = (Point2d(2, 5) to Point2d(11, 1)).square()
//    println(s)
//}

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

    fun solvePart2(): Long {

        fun cross(a: Point2d, b: Point2d, c: Point2d): Double =
            (b.x - a.x.toDouble()) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x)

        fun convexHullJarvis(points: List<Point2d>): List<Point2d> {
            if (points.size <= 1) return points

            val start = points.minWith(compareBy({ it.x }, { it.y }))
            val hull = mutableListOf<Point2d>()
            var current = start

            while (true) {
                hull.add(current)
                var candidate = points.first { it != current }

                for (p in points) {
                    if (p == current || p == candidate) continue
                    val c = cross(current, candidate, p)
                    if (c > 0) {
                        candidate = p
                    } else if (c == 0.0) {
                        val distCandidate = (candidate.x - current.x) * (candidate.x - current.x) +
                                (candidate.y - current.y) * (candidate.y - current.y)
                        val distP = (p.x - current.x) * (p.x - current.x) +
                                (p.y - current.y) * (p.y - current.y)
                        if (distP > distCandidate) {
                            candidate = p
                        }
                    }
                }

                current = candidate
                if (current == start) break
            }

            return hull
        }

        val poly: List<Point2d> = convexHullJarvis(points)

        fun inConvexRegion(p: Point2d): Boolean {
            val n = poly.size
            var sign = 0.0

            for (i in 0 until n) {
                val a = poly[i]
                val b = poly[(i + 1) % n]
                val c = cross(a, b, p)

                if (c != 0.0) {
                    if (sign == 0.0) sign = c
                    else if (sign * c < 0) return false
                }
            }
            return true
        }

        return points.pairs()
            .filter {
                val point3 = Point2d(it.second.x, it.first.y)
                val point4 = Point2d(it.first.x, it.second.y)
                inConvexRegion(point3) && inConvexRegion(point4)
            }
            .maxOf { it.square() }
    }
}