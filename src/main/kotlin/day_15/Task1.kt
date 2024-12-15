package day_15

class Task1 {

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