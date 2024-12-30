package day_20

import java.util.PriorityQueue

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

fun passBoard(begin: Point, end: Point, walls: Set<Point>, processStep: (Point) -> Unit = {}): Int {
    val queue = PriorityQueue<Pair<Point, Int>>(compareBy { (_, time) -> time })
    processStep(begin)
    val visited = mutableSetOf(begin)
    queue.add(begin to 0)
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




