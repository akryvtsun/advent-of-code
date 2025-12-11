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
        val state = ArrayDeque<String>()
        state.add(init)

        do {
            val current = state.removeFirst()
            if (current == "out") {
                count++
                continue
            }
            val to = graph[current]!!
            state.addAll(to)
        } while (state.isNotEmpty())

        return count
    }

    fun solvePart2(): Long {

        val START = "svr"
        val POINT1 = "dac"
        val POINT2 = "fft"
        val END = "out"

        data class State(val passed: Set<String>, val last: String)

        data class State2(val start: String, val notPass: Set<String>, val end: String)

        val memo = mutableMapOf<State2, Long>()

        fun track(start: String, notPass: Set<String>, end: String): Long {
            println("start: $start, notPass: $notPass, end: $end")

            if (start == end) return 1
            if (start in notPass) return 0

            val to = graph[start]!!
            return to.sumOf {
                val newSearch = State2(it, notPass, end)

                if (newSearch in memo) {
                    memo[newSearch]!!
                } else {
                    val count = track(it, notPass, end)
                    memo[newSearch] = count
                    count
                }
            }
        }

        val pass1 =
            track(start = START, notPass = setOf(POINT2, END), end = POINT1) *
            track(start = POINT1, notPass = setOf(START, END), end = POINT2) *
            track(start = POINT2, notPass = setOf(START, POINT1), end = END)

        val pass2 =
            track(start = START, notPass = setOf(POINT1, END), end = POINT2) *
            track(start = POINT2, notPass = setOf(START, END), end = POINT1) *
            track(start = POINT1, notPass = setOf(START, POINT2), end = END)

        return pass1 + pass2
    }
}