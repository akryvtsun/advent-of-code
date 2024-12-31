package year_2024.day_06

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger

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

data class Position(val y: Int, val x: Int) {
    fun move(dir: Direction) = Position(y + dir.dy, x + dir.dx)
}

class Task2 {

    data class State(val pos: Position, val dir: Direction)

    companion object {

        fun solve(
            height: Int, width: Int, obs: List<Position>,
            initPos: Position, initDir: Direction
        ): Int {

            fun hasLoop(obstacles: List<Position>): Boolean {
                var curPos = initPos
                var curDir = initDir
                val visited = mutableSetOf(State(curPos, curDir))
                while (curPos.y in 0 until height && curPos.x in 0 until width) {
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
                    for (obsX in 0 until width) {
                        launch { if (hasLoop(obs + Position(obsY, obsX))) count.incrementAndGet() }
                    }
                }
            }
            return count.get()
        }
    }
}