package day_10

import day_10.Task1.Point

class Task2 {

    data class IslandMap(private val map: List<List<Int>>) {
        operator fun get(p: Point) = map[p.y][p.x]

        fun height() = map.size
        fun width() = map[0].size

        fun isStart(p: Point) = this[p] == 0
        fun isEnd(p: Point) = this[p] == 9

        fun isRouteStep(current: Point, next: Point) = this[next] - this[current] == 1

        fun isInMap(p: Point) =
            p.y in 0 until height() &&
            p.x in 0 until width() &&
            this[p] != BLOCK

        fun getRate(p: Point): Int {
            val routs = mutableSetOf<List<Point>>()

            fun getRateImpl(p: Point, route: List<Point>) {
                val newRoute = route + p
                if (isEnd(p)) {
                    routs += newRoute
                }
                else {
                    val newPs = Direction.entries
                        .map { p + it }
                        .filter { isInMap(it) }
                        .filter { isRouteStep(p, it) }
                    newPs.forEach { getRateImpl(it, newRoute) }
                }
            }

            getRateImpl(p, emptyList())
            return routs.size
        }
    }

    data class Point(val y: Int, val x: Int) {
        operator fun plus(d: Direction) = Point(this.y + d.y, this.x + d.x)
    }

    enum class Direction(val y: Int, val x: Int) {
        UP(-1, 0),
        DOWN(1, 0),
        LEFT(0, -1),
        RIGHT(0, 1)
    }

    companion object {

        fun solve(input: IslandMap): Int {
            return sequence {
                for (y in 0 until input.height()) {
                    for (x in 0 until input.width()) {
                        yield(Point(y, x))
                    }
                }
            }
                .filter { input.isStart(it) }
                .sumOf { input.getRate(it) }
        }

    }
}