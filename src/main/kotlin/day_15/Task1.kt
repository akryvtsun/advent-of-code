package day_15

class Task1 {

    companion object {

        private fun roundMove(value: Int, delta: Int, border: Int): Int {
            val newValue = (value + delta) % border
            return if (newValue < 0) border + newValue else newValue
        }

        fun solve(height: Int, width: Int, robots: List<Robot>): Long {

            fun move(unit: Robot, rounds: Int): Point {
                var curY = unit.coord.y
                var curX = unit.coord.x
                repeat(rounds) {
                    curY = roundMove(curY, unit.delta.y, height)
                    curX = roundMove(curX, unit.delta.x, width)
                }
                return Point(curY, curX)
            }

            fun quadrant(c: Point): Int {
                val y0 = height/2
                val x0 = width/2
                return if (c.y < y0) {
                    if (c.x < x0) {
                        1
                    } else if (c.x > x0) {
                        2
                    }
                    else {
                        0
                    }
                } else if (c.y > y0) {
                    if (c.x < x0) {
                        3
                    } else if (c.x > x0) {
                        4
                    }
                    else {
                        0
                    }
                }
                else {
                    0
                }
            }

            val newCoords = robots.map { move(it, 100) }
            val securityMap = newCoords.map { it to quadrant(it) }.groupBy({ it.second }, { it.first })
            return securityMap
                .filter { it.key != 0 }
                .map { it.value.size }
                .fold(1) { acc, num -> acc * num }
        }
    }
}