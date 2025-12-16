package year_2024

import AocDay
import java.util.PriorityQueue

typealias Keypad = Map<Char, Point>
typealias Grid<T> = Map<Point, T>

class Day21(input: String) : AocDay<Int, Long>(input) {

    companion object {
        val NORTH = Point(-1, 0)
        val EAST = Point(0, 1)
        val SOUTH = Point(1, 0)
        val WEST = Point(0, -1)

        enum class Direction(val c: Char, val delta: Point) {
            UP('^', Point(-1, 0)),
            RIGHT('>', Point(0, 1)),
            DOWN('v', Point(1, 0)),
            LEFT('<', Point(0, -1));
        }

        val numericKeypad = buildMap {
            put('7', Point(0, 0)); put('8', Point(0, 1)); put('9', Point(0, 2))
            put('4', Point(1, 0)); put('5', Point(1, 1)); put('6', Point(1, 2))
            put('1', Point(2, 0)); put('2', Point(2, 1)); put('3', Point(2, 2))
            put('0', Point(3, 1)); put('A', Point(3, 2))
        }

        val directionalKeypad = buildMap {
            put('^', Point(0, 1)); put('A', Point(0, 2))
            put('<', Point(1, 0)); put('v', Point(1, 1)); put('>', Point(1, 2))
        }

        private fun findPaths(begin: Point, end: Point, keypad: Map<Char, Point>): Set<String> {
            val paths = mutableSetOf<String>()
            var minLength = Int.MAX_VALUE

            val queue = PriorityQueue<Pair<Point, String>>(compareBy { it.second.length })
            queue.add(begin to "")
            while (true) {
                val (pos, path) = queue.remove()
                if (pos == end) {
                    if (minLength >= path.length) {
                        minLength = path.length
                        paths += path + 'A'
                    } else
                        return paths
                }
                val next = Direction.entries
                    .map { pos + it.delta to path + it.c }
                    .filter { (nextPos, _) -> keypad.containsValue(nextPos) }
                queue.addAll(next)
            }
        }

        private fun cartesian(lists: List<Set<String>>): List<List<String>> {
            return lists.fold(listOf(listOf())) { acc, set ->
                acc.flatMap { combination ->
                    set.map { element -> combination + element }
                }
            }
        }

        private fun keypadPaths(code: String, keypad: Map<Char, Point>): Set<String> {
            val paths = "A$code"
                .zipWithNext()
                .map { (start, stop) ->
                    val begin = keypad[start]!!
                    val end = keypad[stop]!!
                    findPaths(begin, end, keypad)
                }
            return cartesian(paths).map { it.joinToString(separator = "") }.toSet()
        }

        fun keypadPaths(codes: Set<String>, keypad: Map<Char, Point>): Set<String> {
            val pq = PriorityQueue<String>(compareBy { it.length })
            pq.addAll(codes.flatMap { keypadPaths(it, keypad) })
            val minPathLength = pq.element().length
            return pq.filter { it.length == minPathLength }.toSet()
        }

        fun shortestPath(code: String): String {
            var paths = keypadPaths(setOf(code), numericKeypad)
            repeat(2) {
                paths = keypadPaths(paths, directionalKeypad)
            }
            return paths.first()
        }
    }

    val pads = input.lines()

    private fun complexity(pad: String, seq: String) =
        pad.substringBefore('A').toInt() * seq.length

    override fun solvePart1(): Int {
        return pads.sumOf { complexity(it, shortestPath(it)) }
    }

    val numbersPad = """
            789
            456
            123
            -0A
        """.trimIndent().toKeypad()

    val directionPad = """
            -^A
            <v>
        """.trimIndent().toKeypad()

    fun String.toGrid(): Grid<Char> =
        lines().flatMapIndexed { y, line -> line.mapIndexed { x, c -> Point(y, x) to c } }.toMap()

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
        val keypad = if (first) numbersPad else directionPad
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

    override fun solvePart2(): Long {
        return pads.sumOf { it.sizeOfCommands(25) * it.substringBefore('A').toInt() }
    }
}