package day_19

import java.util.PriorityQueue

class Task1 {

    companion object {

        fun solve(patterns: List<String>, designs: List<String>): Int {
            return designs.count { isPossible(it, patterns).also { p -> println("$it=$p") } }
        }

        private fun isPossible(design: String, patterns: List<String>): Boolean {
            val queue = PriorityQueue<String>(compareBy { it.length }).also { it.add(design) }
            while (true) {
                if (queue.isEmpty()) return false
                val tail = queue.poll()
                val newTails = patterns
                    .filter { tail.startsWith(it) }
                    .map { tail.removePrefix(it) }
                if (newTails.any { it.isEmpty() }) return true
                queue.addAll(newTails)
            }
        }
    }
}