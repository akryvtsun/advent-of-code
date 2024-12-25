package day_21

import java.util.PriorityQueue

class Task2 {

    companion object {

        /**
         * Returns the first key corresponding to the given [value], or `null`
         * if such a value is not present in the map.
         */
        fun <K, V> Map<K, V>.getKey(value: V) =
            entries.firstOrNull { it.value == value }?.key

        private fun findPaths(begin: Point, end: Point, keypad: Map<Char, Point>): Set<String> {
            val paths = mutableSetOf<String>()
            var minLength = Int.MAX_VALUE

            val queue = PriorityQueue<Pair<Point, String>>(compareBy { it.second.length })
            queue.add(begin to "")
            while (true) {
                val (pos, path) = queue.remove()
                if (pos == end) {
                    if (minLength >= path.length) {
                        minLength = path.length
                        paths += path + 'A'
                    }
                    else
                        return paths
                }
                val next = Direction.entries
                    .map { pos + it.delta to path + it.c }
                    .filter { (nextPos, _) -> keypad.getKey(nextPos) != null }
                queue.addAll(next)
            }
        }

        private fun cartesian(lists: List<Set<String>>): List<List<String>> {
            return lists.fold(listOf(listOf())) { acc, set ->
                acc.flatMap { combination ->
                    set.map { element -> combination + element }
                }
            }
        }

        private fun keypadPaths(code: String, keypad: Map<Char, Point>): Set<String> {
            val paths = "A$code"
                .zipWithNext()
                .map { (start, stop) ->
                    val begin = keypad[start]!!
                    val end = keypad[stop]!!
                    findPaths(begin, end, keypad)
                }
            return cartesian(paths).map { it.joinToString(separator = "") }.toSet()
        }

        fun keypadPaths(codes: Set<String>, keypad: Map<Char, Point>): Set<String> {
            val pq = PriorityQueue<String>(compareBy { it.length })
            pq.addAll(codes.flatMap { keypadPaths(it, keypad) })
            val minPathLength = pq.element().length
            return pq.filter { it.length == minPathLength }.toSet()
        }

        fun shortestPath(code: String): String {
            var paths = Task1.keypadPaths(setOf(code), numericKeypad)
            repeat(25) {
                println("$it. size=${paths.size}")
                paths = keypadPaths(paths, directionalKeypad)
            }
            return paths.first()
        }

        private fun complexity(pad: String, seq: String) =
            pad.substringBefore('A').toInt() * seq.length

        fun solve(pads: List<String>): Int {
            return pads
                .sumOf { complexity(it, shortestPath(it)) }
        }
    }
}