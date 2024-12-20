package day_19

class Task2 {

    companion object {

        fun solve(patterns: List<String>, designs: List<String>): Long {
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
            return designs.sumOf { d -> variants(d, root).also { println("$d=$it") } }
        }

        private fun variants(design: String, root: Node): Long {
          //  var count = 0
            val queue = mutableMapOf(0 to 1L)
            while (true) {
                if (queue.isEmpty()) return 0
                val tail = queue.entries.take(1).first()
                val tCount = tail.value
                queue.remove(tail.key)
                if (tail.key == design.length) return tCount
//                val newTails = patterns
//                    .filter { tail.startsWith(it) }
//                    .map { tail.removePrefix(it) }
                val newTails = findTails(design, root, tail.key)
            //    count += newTails.count { it == design.length }
                newTails //.filter { it < design.length }
                    .forEach { t ->
                        val value = queue.computeIfAbsent(t) { _ -> 0 }
                        queue[t] = value + tCount
                    }
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