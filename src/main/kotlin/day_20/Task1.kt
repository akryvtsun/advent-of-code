package day_20

import java.util.PriorityQueue

class Task1 {

    data class Point(val y: Int, val x: Int) {
        operator fun plus(other: Point): Point {
            return Point(y + other.y, x + other.x)
        }
    }

    enum class Direction(val delta: Point) {
        UP(Point(-1,0)),
        RIGHT(Point(0,1)),
        DOWN(Point(1,0)),
        LEFT(Point(0,-1)),
    }

    companion object {

        private fun Set<Point>.findWalls(cur: Point): Set<Point> {
            return buildSet {
                Direction.entries
                    .map { cur + it.delta }
                    .filter { it in this@findWalls }
                    .forEach { add(it) }
            }
        }

        private fun passBoard(begin: Point, end: Point, walls: Set<Point>, processStep: (Point) -> Unit = {}): Int {
            val queue = PriorityQueue<Pair<Point, Int>>(compareBy { (_, time) -> time })
            queue.add(begin to 0)
            val visited = mutableSetOf<Point>()
            while (true) {
                val (cur, time) = queue.remove()
                if (cur == end) return time
                val next = Direction.entries
                    .map { cur + it.delta }
                    .filter { it !in walls }
                    .filter { it !in visited }
                next.forEach { processStep(it) }
                visited += next
                queue += next.map { it to time + 1 }
            }
        }

        fun solve(input: String, threshold: Int): Int {
            // transform input
            val data = input.lines()
            val height = data.size
            val width = data.first().length
            var begin: Point? = null
            var end: Point? = null
            val walls = buildSet {
                data.forEachIndexed { y, line ->
                    line.forEachIndexed { x, char ->
                        when (char) {
                            'S' -> begin = Point(y, x)
                            'E' -> end = Point(y, x)
                            '#' -> add(Point(y, x))
                        }
                    }
                }
            }
            // pass map without cheating, calc reference min time, collect all walls nearby
            val wallsNearby = mutableSetOf<Point>()
            val referenceTime = passBoard(begin!!, end!!, walls) { cur -> wallsNearby += walls.findWalls(cur) }
            return wallsNearby
                .filter { it.y in 1..<height-1 && it.x in 1..<width-1}
                // remove each of nearby walls and calc new min times
                .map { passBoard(begin!!, end!!, walls - it) }
                // count if current min time - reference min time >= threshold
                .count { referenceTime - it >= threshold }
        }
    }
}