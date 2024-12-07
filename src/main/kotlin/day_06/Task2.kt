package day_06

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
            height: Int, width: Int, obstacles: List<Position>,
            initPos: Position, initDir: Direction): Int {
            var count = 0

            fun ifMakesLoop(
                initPos: Position, initDir: Direction, obsPos: Position,
                visited: MutableList<State>): Boolean {
                var curPos = initPos
                var curDir = initDir
                while (curPos.y in 0 until height && curPos.x in 0 until width) {
                    val nextPos = curPos.move(curDir)
                    if (nextPos in (obstacles + obsPos))
                        curDir = curDir.turnRight()
                    else
                        curPos = nextPos
                    if (State(curPos, curDir) in visited) return true
                    visited += State(curPos, curDir)
                }
                return false
            }

            val visited = mutableListOf<State>()
            var curPos = initPos
            var curDir = initDir
            visited += State(curPos, curDir)
            while (curPos.y in 0 until height && curPos.x in 0 until width) {
                val nextPos = curPos.move(curDir)
                if (nextPos in obstacles) {
                    curDir = curDir.turnRight()
                } else {
                    if (ifMakesLoop(curPos, curDir, nextPos, visited.toMutableList())) count++
                    curPos = nextPos
                }
                visited += State(curPos, curDir)
            }
            return count
        }
    }
}