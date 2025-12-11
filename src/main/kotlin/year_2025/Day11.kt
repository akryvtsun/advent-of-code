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

        var init = "you"
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

        data class State(val node: String, val dacFft: Boolean = false)

        var init = "svr"
        val state = ArrayDeque<State>()
        state.add(State(init))

        do {
            val currentNode = state.removeFirst()
            val currentFlag = currentNode.dacFft
            if (currentNode.node == "out") {
                if (currentFlag) count++
                continue
            }
            val to = edges[currentNode.node]!!
            for (node in to) {
                if (node == "dac" || node == "fft") {
                    state.add(State(node, true))
                }
                else {
                    state.add(State(node, currentFlag))
                }
            }
        } while (state.isNotEmpty())

        return count
    }
}