package day_19

class Task1 {

    companion object {

        fun solve(patterns: List<String>, designs: List<String>): Int {
            return designs.count { isPossible(it, patterns).also { p -> println("$it=$p") } }
        }

        private fun isPossible(design: String, patterns: List<String>): Boolean {
            val queue = ArrayDeque(listOf(design))
            while (true) {
                if (queue.isEmpty()) return false
                val tail = queue.removeFirst()
                val newTails = patterns
                    .filter { tail.startsWith(it) }
                    .map { tail.removePrefix(it) }
                if (newTails.any { it.isEmpty() }) return true
                queue.addAll(newTails)
            }
        }
    }
}