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

        data class State(val filled: Set<Point>, val remain: List<Figure>)

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

        val stack = ArrayDeque<State>()
        stack.add(State(emptySet(), fs))

        while (stack.isNotEmpty()) {
            val current = stack.removeLast()    // DFS
            if (current.remain.isEmpty()) return true

            val curFigure = current.remain.first()
            for (y in 0 .. height-3) {
                for (x in 0 .. wide-3) {
                    val shift = Point(y, x)
                    var f = curFigure
                    repeat(4) {
                        val mat = materialize(shift, f)
                        if (mat.none { it in current.filled }) {
                            stack.add(State(current.filled + mat, current.remain.drop(1)))
                        }
                        f = f.rotate()
                    }
                }
            }
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
