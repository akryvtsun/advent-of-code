package day_18

import java.util.PriorityQueue

class Task1 {

    companion object {

        fun solve(bytes: List<Point>, length: Int, height: Int, width: Int): Int {
            val brokenСells = bytes.take(length)
            val begin = Point(0, 0)
            val end = Point(height-1, width-1)

            val pq = PriorityQueue<List<Point>>(compareBy { it.size })
            pq.add(listOf(begin))
            while (true) {
                val curPath = pq.remove()
                val curPos = curPath.last()
                if (curPos == end) return curPath.size-1

                Direction.entries
                    .map { curPos + it.delta }
                    .filter { it.y in 0..<height && it.x in 0..<width }
                    .filter { it !in brokenСells }
                    .filter { it !in curPath }
                    .forEach { pq.add(curPath + it) }
            }
        }
    }
}