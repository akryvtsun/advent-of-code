package year_2025

class Day11(input: String) {

    val graph = input.lines()
        .map { line ->
            val inNode = line.substringBefore(":")
            val outNodes = line.substringAfter(":").trim().split(' ').toSet()
            inNode to outNodes
        }
        .associate { it.first to it.second }

    fun solvePart1(): Int {
        var count = 0

        val init = "you"
        val state = ArrayDeque(listOf(init))

        while (state.isNotEmpty()) {
            val current = state.removeFirst()   // BFS. For DFS *.removeLast() is needed
            if (current == "out") {
                count++
                continue
            }
            val next = graph[current]!!
            state += next
        }

        return count
    }

    companion object Part2 {
        const val START = "svr"
        const val POINT1 = "dac"
        const val POINT2 = "fft"
        const val END = "out"
    }

    fun solvePart2(): Long {

        data class Path(val start: String, val notPass: Set<String>, val end: String)

        val memo = mutableMapOf<Path, Long>()

        fun track(state: Path): Long {
            if (state.start == state.end) return 1
            if (state.start in state.notPass) return 0

            val next = graph[state.start]!!
            return next.sumOf {
                val newState = Path(it, state.notPass, state.end)
                memo.getOrPut(newState) { track(newState) }
            }
        }

        val part1 =
            track(Path(start = START, notPass = setOf(POINT2, END), end = POINT1)) *
            track(Path(start = POINT1, notPass = setOf(START, END), end = POINT2)) *
            track(Path(start = POINT2, notPass = setOf(START, POINT1), end = END))

        val part2 =
            track(Path(start = START, notPass = setOf(POINT1, END), end = POINT2)) *
            track(Path(start = POINT2, notPass = setOf(START, END), end = POINT1)) *
            track(Path(start = POINT1, notPass = setOf(START, POINT2), end = END))

        return part1 + part2
    }
}