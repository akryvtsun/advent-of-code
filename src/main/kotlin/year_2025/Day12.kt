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
        val fs = set.flatMapIndexed { idx, count -> List(count) { figures[idx] } }

        data class State(val comb: List<Pair<Point, Figure>>)

        fun Figure.rotate(): Figure =
            Figure(
                listOf(
                    "" + pattern[0][2] + pattern[1][2] + pattern[2][2],
                    "" + pattern[0][1] + pattern[1][1] + pattern[2][1],
                    "" + pattern[0][0] + pattern[1][0] + pattern[2][0],
                )
            )

        fun allStates(fsSet: List<Figure>): List<State> {
            if (fsSet.isEmpty()) return emptyList()

            val result = mutableListOf<State>()

            val head = fsSet.first()
            val tail = fsSet.drop(1)

            val tailStates = allStates(tail)

            fun addHead(shift: Point, f: Figure, states: List<State>): List<State> {
                val newHead = listOf(shift to f)
                return if (states.isEmpty()) {
                    listOf(State(newHead))
                }
                else {
                    states.map { State(listOf(shift to f) + it.comb) }
                }
            }

            for (y in 0..this.height - 3) {
                for (x in 0..this.wide - 3) {
                    val p = Point(y, x)
                    var f = head
                    repeat(4) {
                        result += addHead(p, f, tailStates)
                        f = f.rotate()
                    }
                }
            }

            return result
        }

        fun materialize(shift: Point, f: Figure) = buildList<Point> {
            f.pattern.forEachIndexed { y, string ->
                string.forEachIndexed { x, ch ->
                    if (ch == '#') add(Point(y + shift.y, x + shift.x))
                }
            }
        }

        fun <T> List<T>.allDistinct(): Boolean =
            size == toSet().size

        fun State.notOverlaps() =
            comb.flatMap { (shift, f) -> materialize(shift, f) }.allDistinct()

        return allStates(fs)
            .also { println("size ${it.size}") }
            .any { it.notOverlaps() }
    }

    fun solvePart1(): Int {
        return areas.count {
            println("area: $it")
            val result = it.canBeFilled()
            println("... can be filed = $result")
            result
        }
    }

    fun solvePart2(): Int {
        return UNKNOWN_VALUE
    }
}
