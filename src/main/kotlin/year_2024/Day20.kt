package year_2024

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.PriorityQueue
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.abs

class Day20(private val input: String) {

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

    private fun Set<Point>.findWalls(cur: Point): Set<Point> {
        return buildSet {
            Direction.entries
                .map { cur + it.delta }
                .filter { it in this@findWalls }
                .forEach { add(it) }
        }
    }

    fun solvePart1(threshold: Int): Int {
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

        val count = AtomicInteger(0)
        runBlocking(Dispatchers.Default) {
            wallsNearby
                .filter { it.y in 1..<height - 1 && it.x in 1..<width - 1 }
                .forEach {
                    launch {
                        // remove each of nearby walls and calc new min times
                        val time = passBoard(begin!!, end!!, walls - it)
                        // count if current min time - reference min time >= threshold
                        if (referenceTime - time >= threshold) {
                            count.incrementAndGet()
                        }
                    }
                }
        }
        return count.get()
    }

    fun solvePart2(threshold: Int): Int {
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