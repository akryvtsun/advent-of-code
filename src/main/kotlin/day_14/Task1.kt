package day_14

class Task1 {

    companion object {

        fun solve(height: Int, width: Int, robots: List<Robot>): Long {

            fun move(unit: Robot, rounds: Int): Point {
                var curY = unit.coord.y
                var curX = unit.coord.x
                repeat(rounds) {
                    curY = (curY + unit.delta.y) % height
                    if (curY < height) curY += height
                    curX = (curX + unit.delta.x) % width
                    if (curX < width) curX += width
                }
                return Point(curY, curX)
            }

            fun quadrant(c: Point): Int {
                val y0 = height/2+1
                val x0 = width/2+1
                return if (c.y < y0) {
                    if (c.x < x0) {
                        1
                    } else if (c.x > x0) {
                        2
                    }
                    else {
                        -1
                    }
                } else if (c.y > y0) {
                    if (c.x < x0) {
                        3
                    } else if (c.x > x0) {
                        4
                    }
                    else {
                        -1
                    }
                }
                else {
                    -1
                }
            }

            val newCoords = robots.map { move(it, 100) }
            val securityMap = newCoords.map { it to quadrant(it) }.groupBy({ it.second }, { it.first })
            return securityMap
                .filter { it.key != -1 }
                .map { it.value.size }
                .fold(1) { acc, num -> acc * num }
        }
    }
}