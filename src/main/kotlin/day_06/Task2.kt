package day_06

class Task2 {

    enum class Direction(val dy: Int, val dx: Int) {
           UP(-1, 0),
        RIGHT(0, +1),
         DOWN(+1, 0),
         LEFT( 0, -1)
    }

    companion object {

        fun solve(map: Board): Int {
            var count = 0

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
                        count += checkObsPosition(map, y, x, direction)
                        y += direction.dy
                        x += direction.dx
                        map[y][x] = 'X'
                    }
                }
                catch (e: Exception) {
                    finished = true
                }
            }
            return count
        }

        private fun checkObsPosition(map: Board, y: Int, x: Int, direction: Direction): Int {
            val checkDir = direction.turnRight()
            var newX = x
            var newY = y
            var allVisited = true
            do {
                newY += checkDir.dy
                newX += checkDir.dx
                if (map[newY][newX] == '.') {
                    allVisited = false
                    break
                }
            } while (map[newY][newX] == '#')
            return if (allVisited) 1 else 0
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