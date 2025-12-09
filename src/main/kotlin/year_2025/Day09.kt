package year_2025

import kotlin.math.abs

data class Point2d(val x: Int, val y: Int)

fun Pair<Point2d, Point2d>.square(): Long =
    (abs(first.x - second.x) + 1L) * (abs(first.y - second.y) + 1)

fun cross(a: Point2d, b: Point2d, c: Point2d): Double =
    (b.x.toDouble() - a.x.toDouble()) * (c.y.toDouble() - a.y.toDouble()) -
    (b.y.toDouble() - a.y.toDouble()) * (c.x.toDouble() - a.x.toDouble())

//fun main() {
//    val s = (Point2d(2, 5) to Point2d(11, 1)).square()
//    println(s)
//}

//fun main() {
//    println(cross(Point2d(7, 3), Point2d(2, 3), Point2d(11, 1)))
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

        fun distance(p1: Point2d, p2: Point2d) =
            (abs(p1.x - p2.x) + 1) + (abs(p1.y - p2.y) + 1)

        fun convexHull2(points: List<Point2d>): List<Point2d> {
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

            return hull
        }

        val poly: List<Point2d> = convexHull2(points)
        val poly2: List<Point2d> = convexHullJarvis(points)

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

//        fun inConvexRegion(p: Point2d): Boolean {
//            val ss = (poly + poly.first())
//                .zipWithNext()
//                .map { (p1, p2) -> cross(p1, p2, p) }
//                .map(Double::sign)
//                .map { it.toInt() }
//            return ss.all { it == ss.first() || it == 0 }
//        }

        return points.pairs()
            .filter {
                val point3 = Point2d(it.second.x, it.first.y)
                val point4 = Point2d(it.first.x, it.second.y)
                inConvexRegion(point3) && inConvexRegion(point4)
            }
            .maxOf { it.square() }
    }
}