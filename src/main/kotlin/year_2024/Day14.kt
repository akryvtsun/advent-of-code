package year_2024

class Day14(
    val input: String,
    val height: Int, val width: Int
) {

    data class Robot(val coord: Point, val delta: Point)

    fun getPoint(data: String): Pair<Int, Int> {
        val (x, y) = """[pv]=(.+),(.+)""".toRegex().find(data)!!.destructured
        return x.toInt() to y.toInt()
    }

    fun transform(input: String) = input.lines()
        .map { line ->
            val (c, d) = line.split(" ")
            val coord = getPoint(c).let { Point(it.second, it.first) }
            val delta = getPoint(d).let { Point(it.second, it.first) }
            Robot(coord, delta)
        }

    fun roundMove(value: Int, delta: Int, border: Int): Int {
        val newValue = (value + delta) % border
        return if (newValue < 0) border + newValue else newValue
    }

    fun solvePart1(): Int {
        val data = transform(input)
        return solve1(height, width, data)
    }

    fun solve1(height: Int, width: Int, robots: List<Robot>): Int {

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
            val y0 = height / 2
            val x0 = width / 2
            return if (c.y < y0) {
                if (c.x < x0) {
                    1
                } else if (c.x > x0) {
                    2
                } else {
                    0
                }
            } else if (c.y > y0) {
                if (c.x < x0) {
                    3
                } else if (c.x > x0) {
                    4
                } else {
                    0
                }
            } else {
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

    fun solvePart2(): Int {
        val data = transform(input)
        return solve2(height, width, data)
    }

    // TODO rework Christmas tree detection logic
    fun solve2(height: Int, width: Int, robots: List<Robot>): Int {

        fun Robot.doStep(): Robot {
            var newY = roundMove(this.coord.y, this.delta.y, height)
            var newX = roundMove(this.coord.x, this.delta.x, width)
            return Robot(Point(newY, newX), this.delta)
        }

        fun checkTree(robots: List<Robot>): Boolean {
            for (y in 0..height) {
                var line = StringBuilder()
                for (x in 0..width) {
                    val char = if (robots.any { it.coord == Point(y, x) }) '#' else ' '
                    line.append(char)
                }
                val found = line.indexOf("########") != -1
                if (found) return true
            }
            return false
        }

        val treeState = generateSequence(0 to robots) { state ->
            state.first + 1 to state.second.map { it.doStep() }
        }
            .first { checkTree(it.second) }
        println(">>>>>>>>>>>>>>>>> STEP: ${treeState.first} >>>>>>>>>>>>>>>>>")
        for (y in 0..height) {
            var line = StringBuilder()
            for (x in 0..width) {
                val char = if (treeState.second.any { it.coord == Point(y, x) }) '#' else ' '
                line.append(char)
            }
            println(line)
        }
        return treeState.first
    }
}