package day_06

class Task2 {

    enum class Direction(val gliph: Char, val dy: Int, val dx: Int) {
           UP('^', -1, 0),
        RIGHT('>',0, +1),
         DOWN('V', +1, 0),
         LEFT( '<',0, -1)
    }

    companion object {

        fun solve(map: Board): Int {
            var count = 0

            var direction = Direction.UP
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
            map[y][x] = direction.gliph

            var finished = false
            while (!finished) {
                try {
                    if (map[y + direction.dy][x + direction.dx] == '#')
                        direction = direction.turnRight()
                    else {
                        count += checkObsPosition(map, y, x, direction)
                        y += direction.dy
                        x += direction.dx
                        map[y][x] = direction.gliph
                    }
                }
                catch (e: Exception) {
                    finished = true
                }
            }
            return count
        }

        private fun checkObsPosition(map: Board, oldY: Int, oldX: Int, oldDirection: Direction): Int {
            var y = oldY
            var x = oldX
            var direction = oldDirection.turnRight()
            try {
                do {
                    if (map[y + direction.dy][x + direction.dx] == '#')
                        direction = direction.turnRight()
                    else {
                        y += direction.dy
                        x += direction.dx
                    }
                } while (direction.gliph != map[y][x])
                return 1
            } catch (e: Exception) {
                return 0
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