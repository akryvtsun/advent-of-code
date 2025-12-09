package year_2025

import UNKNOWN_VALUE
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

    fun solvePart2(): Int {
        return UNKNOWN_VALUE
    }
}