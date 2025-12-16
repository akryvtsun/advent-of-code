package year_2025

import AocDay
import kotlin.math.max
import kotlin.math.min

class Day09(input: String) : AocDay<Long, Long>(input) {

    data class Point2d(val x: Int, val y: Int)

    val points = input.lines()
        .map {
            it.split(",")
                .map(String::toInt)
                .let { (x, y) -> Point2d(x, y) }
        }

    data class Rect(val minX: Int, val maxX: Int, val minY: Int, val maxY: Int) {

        constructor(a: Point2d, b: Point2d) : this(
            min(a.x, b.x),
            max(a.x, b.x),
            min(a.y, b.y),
            max(a.y, b.y)
        )

        fun intersects(line: Line) =
            if (line.isHorizontal()) {
                if (line.a.y in (minY + 1)..<maxY) {
                    val segMinX = min(line.a.x, line.b.x)
                    val segMaxX = max(line.a.x, line.b.x)
                    overlaps(segMinX, segMaxX, minX, maxX)
                } else
                    false
            } else {
                // line is vertical
                if (line.a.x in (minX + 1)..<maxX) {
                    val segMinY = min(line.a.y, line.b.y)
                    val segMaxY = max(line.a.y, line.b.y)
                    overlaps(segMinY, segMaxY, minY, maxY)
                } else
                    false
            }

        fun square(): Long = (maxX - minX + 1L) * (maxY - minY + 1L)

        private fun overlaps(innerMin: Int, innerMax: Int, outerMin: Int, outerMax: Int): Boolean =
            innerMax > outerMin && innerMin < outerMax
    }

    override fun solvePart1(): Long = points.pairs()
        .map { (p1, p2) -> Rect(p1, p2) }
        .maxOf(Rect::square)

    class Line(a: Point2d, b: Point2d) {
        val a: Point2d
        val b: Point2d

        init {
            require(a.x == b.x || a.y == b.y)
            this.a = a
            this.b = b
        }

        fun isHorizontal() = a.y == b.y

        operator fun contains(p: Point2d): Boolean =
            if (a.x == b.x) {                        // vertical line
                p.x == a.x && p.y in min(a.y, b.y)..max(a.y, b.y)
            } else {                                 // horizontal line
                p.y == a.y && p.x in min(a.x, b.x)..max(a.x, b.x)
            }
    }

    override fun solvePart2(): Long {

        val hull = (points + points.first()).zipWithNext(::Line)

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
                val (p1, p2) = pair
                val rect = Rect(p1, p2)
                val p3 = Point2d(pair.second.x, pair.first.y)
                val p4 = Point2d(pair.first.x, pair.second.y)

                isInHull(p3) && isInHull(p4) &&
                        hull.none(rect::intersects)
            }
            .map { (p1, p2) -> Rect(p1, p2) }
            .maxOf(Rect::square)
    }
}