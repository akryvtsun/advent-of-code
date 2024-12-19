package day_19

class Task2 {

    companion object {

        fun solve(patterns: List<String>, designs: List<String>): Int {
            return designs.sumOf { variants(it, patterns) }
        }

        private fun variants(design: String, patterns: List<String>): Int {
            var count = 0
            val queue = ArrayDeque(listOf(design))
            while (true) {
                if (queue.isEmpty()) return count
                val tail = queue.removeFirst()
                val newTails = patterns
                    .filter { tail.startsWith(it) }
                    .map { tail.removePrefix(it) }
                count += newTails.count { it.isEmpty() }
                queue.addAll(newTails.filter { it.isNotEmpty() })
            }
        }
    }
}