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

    fun solvePart2(): Long {
        var count = 0L

        val init = "svr"
        val targetNodes = setOf("dac", "fft")

        data class State(val passed: Set<String>, val last: String)

        val state = ArrayDeque<State>()
        state += State(setOf(init), init)

        while (state.isNotEmpty()) {
            val current = state.removeLast() // DFS

            if (current.last == "out") {
                if (current.passed.containsAll(targetNodes)) {
                    count++
                    if ((count % 100).toInt() == 0)
                        println(count)
                }
            }
            else {
                val to = edges[current.last]!!
                for (next in to) {
                    if (next !in current.passed) {
                        state += State(current.passed + next, next)
                    }
                }
            }
        }

        return count
    }
}