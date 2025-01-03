package year_2024.day_15

import year_2024.Point

class Task1 {

    class Warehouse(map: String) {

        val obstacles = mutableListOf<Point>()
        val boxes = mutableListOf<Point>()
        var robot: Point? = null

        init {
            val data = map.lines()
            for (y in data.indices) {
                for (x in data.first().indices) {
                    val point = Point(y, x)
                    when (data[y][x]) {
                        '#' -> obstacles += point
                        'O' -> boxes += point
                        '@' -> robot = point
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
                if (next in boxes) {
                    var nextBox = next
                    do {
                        nextBox += cmd.delta
                    } while (nextBox in boxes)
                    if (nextBox in obstacles) {
                        continue // boxes close to obstacle in cmd direction - skip move
                    }
                    else {  // move first box in chain to finishing space
                        boxes -= next
                        boxes += nextBox
                    }
                }
                cur = next // move robot to new position in cmd direction
            }
            return boxes
                .sumOf { it.y * 100 + it.x }
        }
    }
}