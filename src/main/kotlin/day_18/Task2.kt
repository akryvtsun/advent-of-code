package day_18

import java.util.PriorityQueue
import kotlin.math.absoluteValue

class Task2 {

    companion object {

        fun solve(bytes: List<Point>, height: Int, width: Int): Point {

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
}