package day_06

typealias MapBoard = List<List<MutableSet<Char>>>

class Task2 {

    enum class Direction(val gliph: Char, val dy: Int, val dx: Int) {
           UP('^', -1, 0),
        RIGHT('>',0, +1),
         DOWN('V', +1, 0),
         LEFT( '<',0, -1)
    }

    companion object {

        fun solve(map: MapBoard): Int {
            var count = 0

            var direction = Direction.UP
            var x = 0
            var y = 0
            map.forEachIndexed() { i, row ->
                val idx = row.indexOfFirst { direction.gliph in it }
                if (idx != -1) {
                    y = i
                    x = idx
                    return@forEachIndexed
                }
            }

            var finished = false
            while (!finished) {
                try {
                    if (map[y + direction.dy][x + direction.dx] == setOf('#')) {
                        direction = direction.turnRight()
                    }
                    else {
                        count += checkObsPosition(map, y, x, direction)
                        y += direction.dy
                        x += direction.dx
                        //println("$y:$x")
                    }
                    map[y][x].add(direction.gliph)
                }
                catch (e: Exception) {
                    finished = true
                }
            }
            return count
        }

        private fun checkObsPosition(map: MapBoard, oldY: Int, oldX: Int, oldDirection: Direction): Int {
            var y = oldY
            var x = oldX
            var direction = oldDirection.turnRight()
            try {
                while (direction.gliph !in map[y][x]) {
                    if (map[y + direction.dy][x + direction.dx] == setOf('#'))
                        direction = direction.turnRight()
                        //return 0
                    else {
                        y += direction.dy
                        x += direction.dx
                    }
                    println("$y:$x")
                }
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