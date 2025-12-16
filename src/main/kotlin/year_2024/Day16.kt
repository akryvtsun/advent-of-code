package year_2024

import AocDay
import java.util.PriorityQueue

class Day16(input: String) : AocDay<Int, Int>(input) {

    enum class Direction(val delta: Point) {
        UP(Point(-1, 0)),
        RIGHT(Point(0, 1)),
        DOWN(Point(1, 0)),
        LEFT(Point(0, -1));
    }

    data class Step(val pos: Point, val dir: Direction)

    data class Labyrinth(val start: Point, val end: Point, val obstacles: Set<Point>)

    fun transform(input: String): Labyrinth {
        var start: Point? = null
        var end: Point? = null
        val obstacles = mutableSetOf<Point>()
        val lines = input.lines()
        for (y in lines.indices) {
            for (x in lines.first().indices) {
                when (lines[y][x]) {
                    '#' -> obstacles.add(Point(y, x))
                    'S' -> start = Point(y, x)
                    'E' -> end = Point(y, x)
                }
            }
        }
        return Labyrinth(start!!, end!!, obstacles)
    }

    val config = transform(input)

    override fun solvePart1(): Int {
        val initPos = Step(config.start, Direction.RIGHT)
        val unvisited = PriorityQueue<Pair<Step, Int>>(compareBy { it.second })
        unvisited.add(initPos to 0)
        val visited = mutableSetOf<Step>()
        while (unvisited.isNotEmpty()) {
            val path = unvisited.poll().also { visited += it.first }
            val curStep = path.first
            if (curStep.pos == config.end) {
                return path.second
            } else {
                unvisited += listOf(-1, 0, 1)
                    .map {
                        var newPos = curStep.pos
                        val newDir =
                            Direction.entries[((curStep.dir.ordinal + it) + Direction.entries.size) % Direction.entries.size]
                        var newCount = path.second
                        if (it == 0) {
                            newPos += newDir.delta
                            newCount += 1
                        } else {
                            newCount += 1000
                        }
                        val newStep = Step(newPos, newDir)
                        newStep to newCount
                    }
                    .filter { it.first.pos !in config.obstacles }
                    .filter { it.first !in visited }
            }
        }
        return -1
    }

    override fun solvePart2(): Int {
        val initPos = Step(config.start, Direction.RIGHT)
        val unvisited = PriorityQueue<Pair<List<Step>, Int>>(compareBy { it.second })
        unvisited.add(listOf(initPos) to 0)
        var best: Int? = null
        val res = mutableSetOf<Point>()
        val visited = mutableSetOf<Step>()
        while (unvisited.isNotEmpty()) {
            val path = unvisited.poll().also { visited += it.first }
            val curStep = path.first.last()
            if (curStep.pos == config.end) {
                if (best == null) {
                    best = path.second
                    res += path.first.map { it.pos }
                } else if (path.second == best) {
                    res += path.first.map { it.pos }
                }
            } else {
                if (best == null || path.second < best) {
                    unvisited += listOf(-1, 0, 1)
                        .map {
                            var newPos = curStep.pos
                            val newDir =
                                Direction.entries[((curStep.dir.ordinal + it) + Direction.entries.size) % Direction.entries.size]
                            var newCount = path.second
                            if (it == 0) {
                                newPos += newDir.delta
                                newCount += 1
                            } else {
                                newCount += 1000
                            }
                            val newStep = Step(newPos, newDir)
                            newStep to newCount
                        }
                        .filter { it.first.pos !in config.obstacles }
                        .filter { it.first !in visited }
                        .map { (path.first + it.first) to it.second }
                }
            }
        }
        return res.size
    }
}