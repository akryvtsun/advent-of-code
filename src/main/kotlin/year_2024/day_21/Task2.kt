package year_2024.day_21

typealias Point = Pair<Int, Int>
val Point.y get() = first
val Point.x get() = second

val NORTH: Point = -1 to 0
val EAST: Point = 0 to 1
val SOUTH: Point = 1 to 0
val WEST: Point = 0 to -1

operator fun Point.plus(other: Point) = y + other.y to x + other.x

typealias Keypad = Map<Char, Point>
typealias Grid<T> = Map<Point, T>

fun String.toGrid(): Grid<Char> = lines().flatMapIndexed { y, line -> line.mapIndexed { x, c -> y to x to c } }.toMap()

class Task2 {

    companion object {

        val numberspad = """
            789
            456
            123
            -0A
        """.trimIndent().toKeypad()

        val directionpad = """
            -^A
            <v>
        """.trimIndent().toKeypad()

        fun String.toKeypad(): Keypad = toGrid().entries.associateBy({ it.value }, { it.key })

        fun List<Point>.toCharacters() = map {
            when (it) {
                NORTH -> '^'
                SOUTH -> 'v'
                EAST -> '>'
                WEST -> '<'
                else -> error("invalid direction")
            }
        }.joinToString("", postfix = "A")

        fun State.routes(keypad: Keypad): List<String> {
            val start = keypad.getValue(from)
            val goal = keypad.getValue(to)
            val hole = keypad.getValue('-')
            return generateSequence(listOf(start to emptyList<Point>())) { frontier ->
                frontier.flatMap { (end, path) ->
                    when (end) {
                        hole -> emptyList()
                        else -> listOfNotNull(
                            if (end.x > goal.x) end + WEST to path + WEST else null,
                            if (end.x < goal.x) end + EAST to path + EAST else null,
                            if (end.y > goal.y) end + NORTH to path + NORTH else null,
                            if (end.y < goal.y) end + SOUTH to path + SOUTH else null
                        )
                    }
                }
            }.takeWhile { it.isNotEmpty() }.last().map { (_, line) -> line.toCharacters() }
        }

        val memoization = mutableMapOf<State, Long>()

        fun State.solve(first: Boolean): Long = memoization.getOrPut(this) {
            val keypad = if (first) numberspad else directionpad
            val routes = routes(keypad)
            when (depth) {
                0 -> routes.first().length.toLong()
                else -> routes.minOf { it.sizeOfCommands(depth - 1, false) }
            }
        }

        data class State(val from: Char, val to: Char, val depth: Int)

        fun String.sizeOfCommands(depth: Int, first: Boolean = true): Long = "A$this"
            .zipWithNext { a, b -> State(a, b, depth).solve(first) }
            .sum()

        fun solve(pads: List<String>): Long {
            return pads.sumOf { it.sizeOfCommands(25) * it.substringBefore('A').toInt() }
        }
    }
}