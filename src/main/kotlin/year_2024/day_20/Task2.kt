package year_2024.day_20

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import year_2024.Point
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.abs

class Task2 {

    companion object {

        fun solve(input: String, threshold: Int): Int {
            val cheatLen = 20

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
            // pass map without cheating, calc reference min time and path
            val referencePath = mutableListOf<Point>()
            val referenceTime = passBoard(begin!!, end!!, walls) { referencePath += it }

            fun isInField(pos: Point) = pos.y in 0..<height && pos.x in 0..<width

            val count = AtomicInteger(0)
            // hash map for memoization intermediate results
            val memo = ConcurrentHashMap<Point, Int>()

            runBlocking(Dispatchers.Default) {
                for (i in referencePath.indices) {
                    val cheatStartPos = referencePath[i]
                    for (dy in -cheatLen..cheatLen) {
                        for (dx in -cheatLen..cheatLen) {
                            launch {
                                if (!(dy == 0 && dx == 0)) {
                                    val radius = abs(dy) + abs(dx)
                                    if (radius <= cheatLen) {
                                        val cheatEndPos = cheatStartPos + Point(dy, dx)
                                        if (isInField(cheatEndPos) && cheatEndPos !in walls) {
                                            val time = memo.computeIfAbsent(cheatEndPos) { passBoard(cheatEndPos, end!!, walls) }
                                            if (referenceTime - (i + radius + time) >= threshold) {
                                                count.incrementAndGet()
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return count.get()
        }
    }
}