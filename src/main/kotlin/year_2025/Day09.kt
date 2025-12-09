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

   class Line(a: Point2d, b: Point2d) {
        val a: Point2d
        val b: Point2d

        init {
            require(a.x == b.x || a.y == b.y)
            this.a = a
            this.b = b
        }

        fun isHorizontal() = a.y == b.y

        operator fun contains(p: Point2d) =
            when {
                a.x == b.x && a.x == p.x && (p.y in min(a.y, b.y)..max(a.y, b.y)) -> true
                a.y == b.y && a.y == p.y && (p.x in min(a.x, b.x)..max(a.x, b.x)) -> true
                else -> false
            }
    }

    fun solvePart2(): Long {

        val hull = (points + points.first()).zipWithNext(::Line)

        data class Rect(val minX: Int, val maxX: Int, val minY: Int, val maxY: Int) {

            fun intersects(line: Line) =
                if (line.isHorizontal()) {
                    if (line.a.y in (minY + 1)..<maxY) {
                        val segMinX = min(line.a.x, line.b.x)
                        val segMaxX = max(line.a.x, line.b.x)
                        segMaxX > minX && segMinX < maxX
                    } else
                        false
                } else {
                    // line is vertical
                    if (line.a.x in (minX + 1)..<maxX) {
                        val segMinY = min(line.a.y, line.b.y)
                        val segMaxY = max(line.a.y, line.b.y)
                        segMaxY > minY && segMinY < maxY
                    } else
                        false
                }
        }

        fun Pair<Point2d, Point2d>.toRect(): Rect =
            Rect(
                min(first.x, second.x),
                max(first.x, second.x),
                min(first.y, second.y),
                max(first.y, second.y)
            )

        fun isInHull(p: Point2d): Boolean {
            if (hull.any { p in it }) return true
            val crossings = hull
                .filterNot { it.isHorizontal() }
                .count {
                    val minY = min(it.a.y, it.b.y)
                    val maxY = max(it.a.y, it.b.y)
                    // compare with lines on the right side from the point
                    p.x < it.a.x && p.y in minY..<maxY
                }
            return crossings % 2 == 1
        }

        return points.pairs()
            .filter { pair ->
                val p3 = Point2d(pair.second.x, pair.first.y)
                val p4 = Point2d(pair.first.x, pair.second.y)
                val rect = pair.toRect()

                isInHull(p3) && isInHull(p4) &&
                        hull.none { rect.intersects(it) }
            }
            .maxOf { it.square() }
    }
}