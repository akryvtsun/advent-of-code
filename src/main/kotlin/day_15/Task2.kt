package day_15

class Task2 {

    data class Box(val left: Point, val right: Point) {
        operator fun contains(point: Point): Boolean {
            return point.y == left.y && (point.x in left.x..right.x)
        }

        operator fun plus(point: Point): Box {
            return Box(left + point, right + point)
        }
    }

    class Warehouse(map: String) {

        val obstacles = mutableListOf<Point>()
        val boxes = mutableListOf<Box>()
        var robot: Point? = null

        init {
            val data = map.lines()
            for (y in data.indices) {
                for (x in data.first().indices) {
                    val left = Point(y, x*2)
                    val right = Point(y, x*2+1)
                    when (data[y][x]) {
                        '#' -> obstacles += listOf(left, right)
                        'O' -> boxes += Box(left, right)
                        '@' -> robot = left
                    }
                }
            }
        }
    }

    companion object {

        fun solve(data: Warehouse, commands: List<Command>): Int {
            val obstacles = data.obstacles.toList()
            val boxes = data.boxes
            var cur = data.robot!!
            for (cmd in commands) {
                val next = cur + cmd.delta
                if (next in obstacles) {
                    continue    // skip move
                }
                val b = boxes.firstOrNull { next in it }
                if (b != null) {
                    val bSet = mutableSetOf(b)
                    var nextPos = next
                    while (true) {
                        nextPos += cmd.delta
                        val nb = boxes.firstOrNull { nextPos in it }
                        if (nb == null) break
                        bSet += nb
                    }
                    if (nextPos in obstacles) {
                        continue // boxes close to obstacle in cmd direction - skip move
                    }
                    else {  // move boxes set
                        boxes -= bSet
                        boxes += bSet.map { it + cmd.delta }
                    }
                }
                cur = next // move robot to new position in cmd direction
            }
            return boxes.sumOf { it.left.y * 100 + it.left.x }
        }
    }
}