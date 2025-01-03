package year_2024.day_19

import java.util.PriorityQueue

class Task2 {

    companion object {

        fun solve(patterns: List<String>, designs: List<String>): Long {
            val root = buildPrefixTree(patterns)
            return designs.sumOf { d -> variants(d, root).also { println("$d=$it") } }
        }

        private fun variants(design: String, root: Node): Long {
            val queue = PriorityQueue<Pair<Int, Long>>(compareBy { it.first })
            queue.add(0 to 1L)
            while (true) {
                if (queue.isEmpty()) return 0
                val (tIndex, tCount) = queue.remove()
                if (tIndex == design.length) return tCount
                val newTails = findTails(design, root, tIndex)
                newTails
                    .forEach { t ->
                        var pair = queue.firstOrNull { it.first == t }
                        if (pair != null) {
                            queue.remove(pair)
                        } else {
                            pair = t to 0L
                        }
                        queue.add(t to pair.second + tCount)
                    }
            }
        }
    }
}