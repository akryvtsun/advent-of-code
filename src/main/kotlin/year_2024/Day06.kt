package year_2024

import AocDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger

class Day06(input: String): AocDay<Int, Int>(input) {

    enum class Direction(val dy: Int, val dx: Int) {
        UP(-1, 0),
        RIGHT(0, +1),
        DOWN( +1, 0),
        LEFT( 0, -1);

        fun turnRight(): Direction {
            return when (this) {
                UP -> RIGHT
                RIGHT -> DOWN
                DOWN -> LEFT
                LEFT -> UP
            }
        }
    }

    val map: List<StringBuilder> = input.lines().map { StringBuilder(it) }

    override fun solvePart1(): Int {
        var x = 0
        var y = 0
        map.forEachIndexed() { i, row ->
            val idx = row.indexOf('^')
            if (idx != -1) {
                y = i
                x = idx
                return@forEachIndexed
            }
        }
        map[y][x] = 'X'

        var finished = false
        var direction = Direction.UP
        while (!finished) {
            try {
                if (map[y + direction.dy][x + direction.dx] == '#')
                    direction = direction.turnRight()
                else {
                    y += direction.dy
                    x += direction.dx
                    map[y][x] = 'X'
                }
            }
            catch (_: Exception) {
                finished = true
            }
        }
        return map.sumOf { line ->
            line.count { it == 'X' }
        }
    }

    override fun solvePart2(): Int {

        data class Position(val y: Int, val x: Int) {
            fun move(dir: Direction) = Position(y + dir.dy, x + dir.dx)
        }

        data class State(val pos: Position, val dir: Direction)

        val initDir = Direction.UP

        var initPos: Position? = null
        val obstacles = mutableListOf<Position>()
        var width: Int? = null
        val lines = input.lines()
        val height = lines.size
        lines.forEachIndexed { i, line ->
            line.forEachIndexed { j, char ->
                when (char) {
                    '#' -> obstacles.add(Position(i, j))
                    '^' -> initPos = Position(i, j)
                }
            }
            width = line.length
        }

        fun hasLoop(obstacles: List<Position>): Boolean {
            var curPos = initPos
            var curDir = initDir
            val visited = mutableSetOf(State(curPos!!, curDir))
            while (curPos!!.y in 0 until height && curPos.x in 0 until width!!) {
                val nextPos = curPos.move(curDir)
                if (nextPos in obstacles)
                    curDir = curDir.turnRight()
                else
                    curPos = nextPos
                if (State(curPos, curDir) in visited) return true
                visited += State(curPos, curDir)
            }
            return false
        }

        val count = AtomicInteger(0)
        runBlocking(Dispatchers.Default) {
            for (obsY in 0 until height) {
                for (obsX in 0 until width!!) {
                    launch { if (hasLoop(obstacles + Position(obsY, obsX))) count.incrementAndGet() }
                }
            }
        }
        return count.get()
    }
}