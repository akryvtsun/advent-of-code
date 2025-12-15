package year_2025

import UNKNOWN_VALUE

class Day12(input: String) {

    data class Figure(val pattern: List<String>)
    data class Area(val wide: Int, val height: Int, val set: List<Int>)

    private val data = input.split("\n\n")
    val figures = getFigures(data.dropLast(1))
    val areas = getAreas(data.last())

    fun getFigures(data: List<String>) =
        data.map {
            Figure(it.split("\n").drop(1))
        }

    fun getAreas(data: String) =
        data.split("\n").map {
            val (wide, long) = it.substringBefore(": ").split('x').map(String::toInt)
            val set = it.substringAfter(": ").split(' ').map(String::toInt)
            Area(wide, long, set)
        }

    fun Area.canBeFilled(): Boolean {

        fun Figure.rotate(): Figure =
            Figure(
                listOf(
                    "" + pattern[0][2] + pattern[1][2] + pattern[2][2],
                    "" + pattern[0][1] + pattern[1][1] + pattern[2][1],
                    "" + pattern[0][0] + pattern[1][0] + pattern[2][0],
                )
            )

        fun materialize(shift: Point, f: Figure) = buildSet {
            f.pattern.forEachIndexed { y, string ->
                string.forEachIndexed { x, ch ->
                    if (ch == '#') add(Point(y + shift.y, x + shift.x))
                }
            }
        }

        val fs = set.flatMapIndexed { idx, count -> List(count) { figures[idx] } }

        val rotations = 4
        val points = 9

        val radix = rotations * points
        val max = radix.toBigInteger().pow(fs.size)

        for (n in 0 until max.toLong()) {
            val nRadix = n.toString(radix).padStart(fs.size, '0')

            var result = true
            val memo = mutableSetOf<Point>()
            nRadix.forEachIndexed { index, d ->
                val v = Character.digit(d, 36)

                // define figure rotation
                val r = v % rotations
                var f = fs[index]
                repeat(r) {
                    f = f.rotate()
                }

                // define point shift
                val p = v / rotations
                val point = Point(p / 3, p % 3)

                val mat = materialize(point, f)
                if (mat.none { it in memo }) {
                    memo += mat
                } else {
                    result = false
                    return@forEachIndexed
                }
            }
            if (result) return true
        }
        return false
    }

    fun solvePart1(): Int {
        return areas.count {
            println("area: $it")
            val result = it.canBeFilled()
            println("... can be filled = $result")
            result
        }
    }

    fun solvePart2(): Int {
        return UNKNOWN_VALUE
    }
}
