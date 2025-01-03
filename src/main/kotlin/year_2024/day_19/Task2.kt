package year_2024.day_19

class Task2 {

    companion object {

        fun solve(patterns: List<String>, designs: List<String>): Long {
            val root = buildPrefixTree(patterns)
            return designs.sumOf { d -> variants(d, root).also { println("$d=$it") } }
        }

        private fun variants(design: String, root: Node): Long {
            val lengthMap = mutableMapOf(0 to 1L)
            while (true) {
                if (lengthMap.isEmpty()) return 0
                val (tIndex, tCount) = lengthMap.entries.first()
                lengthMap.remove(tIndex)
                if (tIndex == design.length) return tCount
                val newTails = findTails(design, root, tIndex)
                newTails
                    .filter { it <= design.length }
                    .forEach { t ->
                        val value = lengthMap.getOrPut(t) { 0 }
                        lengthMap[t] = value + tCount
                    }
            }
        }
    }
}