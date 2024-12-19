package day_19

class Task2 {

    companion object {

        fun solve(patterns: List<String>, designs: List<String>): Int {
            // build prefix tree
            val root = Node()
            for (pattern in patterns) {
                var candidate = root
                for (c in pattern) {
                    if (candidate.next == null) {
                        candidate.next = mutableMapOf()
                    }
                    val probe = candidate.next!![c]
                    if (probe == null) {
                        candidate.next!![c] = Node()
                    }
                    candidate = candidate.next!![c]!!
                }
                candidate.isTerminal = true
            }
            return designs.count { isPossible(it, root) }
        }

        private fun isPossible(design: String, root: Node): Boolean {
            val queue = LinkedHashSet(listOf(0))
            while (true) {
                if (queue.isEmpty()) return false
                val tail = queue.removeFirst()
                val newTails = findTails(design, root, tail)
                if (newTails.any { it == design.length }) return true
                queue.addAll(newTails)
            }
        }

        private fun findTails(design: String, root: Node, tail: Int) : List<Int> {
            val newTails = mutableListOf<Int>()
            var i = tail
            var node = root

            while (true) {
                if (node.next == null || i == design.length) break
                val c = design[i]
                val candidate = node.next!![c]
                if (candidate != null && candidate.isTerminal) {
                    newTails += i + 1
                }
                if (candidate == null) break
                node = candidate
                i++
            }

            return newTails
        }
    }
}