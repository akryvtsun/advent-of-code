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

        while (true) {
            if (state.isEmpty()) break
            val current = state.removeFirst()
            if (current == "out") {
                count++
                continue
            }
            val to = edges[current]!!
            state.addAll(to)
        }

        return count
    }

    fun solvePart2(): Int = -1
}