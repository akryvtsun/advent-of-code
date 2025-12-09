package year_2024

import java.util.PriorityQueue
import kotlin.math.absoluteValue

class Day18(input: String) {

    enum class Direction(val delta: Point) {
        UP(Point(-1, 0)),
        RIGHT(Point(0, 1)),
        DOWN(Point(1, 0)),
        LEFT(Point(0, -1));
    }

    fun transform(input: String): List<Point> {
        return input.lines().map { line ->
            val (x, y) = line.split(',')
            Point(y.toInt(), x.toInt())
        }
    }

    val bytes = transform(input)

    fun solvePart1(length: Int, height: Int, width: Int): Int {
        val brokenCells = bytes.take(length).toSet()
        val begin = Point(0, 0)
        val end = Point(height-1, width-1)

        val pq = PriorityQueue<List<Point>>(compareBy { it.size })
        pq.add(listOf(begin))
        val visited = mutableSetOf(begin)
        while (true) {
            val curPath = pq.remove()
            val curPos = curPath.last()
            if (curPos == end) return curPath.size-1

            Direction.entries
                .map { curPos + it.delta }
                .filter { it.y in 0..<height && it.x in 0..<width }
                .filter { it !in brokenCells }
                .filter { it !in visited }
                .forEach { pq.add(curPath + it); visited += it }
        }
    }

    fun solvePart2(height: Int, width: Int): Point {
        val begin = Point(0, 0)
        val end = Point(height-1, width-1)

        fun isExitReachable(brokenCells: Set<Point>): Boolean {
            val pq = PriorityQueue<List<Point>>(compareBy { it.size })
            pq.add(listOf(begin))
            val visited = mutableSetOf(begin)
            while (true) {
                val curPath = pq.poll()
                if (curPath == null) return false
                val curPos = curPath.last()
                if (curPos == end) return true

                Direction.entries
                    .map { curPos + it.delta }
                    .filter { it.y in 0..<height && it.x in 0..<width }
                    .filter { it !in brokenCells }
                    .filter { it !in visited }
                    .forEach { pq.add(curPath + it); visited += it }
            }
        }

        return (1..bytes.size)
            .map(bytes::take)
            .binarySearch {
                if (isExitReachable(it.toSet())) -1 else 1
            }
            .let { bytes[it.absoluteValue - 1] }
            .let { (y, x) -> Point(x, y) }
    }
}