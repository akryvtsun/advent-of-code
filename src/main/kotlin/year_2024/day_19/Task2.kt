package year_2024.day_19

class Task2 {

    companion object {

        fun solve(patterns: List<String>, designs: List<String>): Long {
            val root = buildPrefixTree(patterns)
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
    }
}