package year_2025

class Day11(input: String) {

    val edges = input.lines()
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
            val to = edges[current]!!
            state.addAll(to)
        } while (state.isNotEmpty())

        return count
    }

    fun solvePart2(): Int {
        var count = 0

        data class State(val path: Set<String>, val last: String)

        val init = "svr"
        val state = ArrayDeque<State>()
        state.add(State(setOf(init), init))

        val targetNodes = setOf("dac", "fft")

        do {
            val current = state.removeFirst()
            if (current.last == "out") {
                if (current.path.containsAll(targetNodes)) count++
                continue
            }
            val to = edges[current.last]!!
            for (node in to) {
                if (node !in current.path) {
                    state.add(State(current.path + node, node))
                }
            }
        } while (state.isNotEmpty())

        return count
    }
}