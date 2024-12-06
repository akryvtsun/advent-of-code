package day_06

typealias Board = List<StringBuilder>

class Task1 {

    enum class Direction(val dy: Int, val dx: Int) {
           UP(-1, 0),
        RIGHT(0, +1),
         DOWN(+1, 0),
         LEFT( 0, -1)
    }

    companion object {

        fun solve(map: Board): Int {
            var x: Int = 0
            var y: Int = 0
            map.forEachIndexed() { i, row ->
                val idx = row.indexOf('^')
                if (idx != -1) {
                    y = i
                    x = idx
                    return@forEachIndexed
                }
            }

            var finished = false
            var direction: Direction = Direction.UP
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
                catch (e: Exception) {
                    finished = true
                }
            }
            return map.sumOf { line ->
                line.count { it == 'X' }
            }
        }

        fun Direction.turnRight(): Direction {
            return when (this) {
                Direction.UP -> Direction.RIGHT
                Direction.RIGHT -> Direction.DOWN
                Direction.DOWN -> Direction.LEFT
                Direction.LEFT -> Direction.UP
            }
        }
    }
}