package day_06

typealias MapBoard = List<List<MutableSet<Char>>>

class Task2 {

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

    data class State(val pos: Position, val dir: Direction)

    companion object {

        fun solve(
            height: Int, width: Int, obstacles: List<Position>,
            initPos: Position, initDir: Direction): Int {
            var count = 0

            fun checkObsPosition(
                obsPos: Position, obsDir: Direction,
                visited: MutableList<State>): Int {
                var current = obsPos
                var direction = obsDir.turnRight()
                visited += State(current, direction)
                while (current.y in 0 until height && current.x in 0 until width) {
                    if (State(current, direction) in visited) return 1
                    val next = current.move(direction)
                    if (next in obstacles)
                        direction = direction.turnRight()
                    else {
                        current = next
                        visited += State(current, direction)
                    }
                }
                return 0
            }

            val visited = mutableListOf<State>()
            var current = initPos
            var direction = initDir
            visited += State(current, direction)
            while (current.y in 0 until height && current.x in 0 until width) {
                val next = current.move(direction)
                if (next in obstacles) {
                    direction = direction.turnRight()
                } else {
                    count += checkObsPosition(next, direction, visited.toMutableList())
                    current = next
                    visited += State(current, direction)
                }
            }
            return count
        }
    }
}