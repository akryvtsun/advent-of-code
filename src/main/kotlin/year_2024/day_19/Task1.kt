package year_2024.day_19

class Task1 {

    companion object {

        fun solve(patterns: List<String>, designs: List<String>): Int {
            val root = buildPrefixTree(patterns)
            return designs.count { isPossible(it, root) }
        }

        private fun isPossible(design: String, root: Node): Boolean {
            val queue = LinkedHashSet(listOf(0))
            while (true) {
                if (queue.isEmpty()) return false
                val tail = queue.removeFirst()
                if (tail == design.length) return true
                val newTails = findTails(design, root, tail)
                queue.addAll(newTails)
            }
        }
    }
}