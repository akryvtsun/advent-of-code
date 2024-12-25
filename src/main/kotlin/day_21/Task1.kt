package day_21

import java.util.PriorityQueue

data class Point(val y: Int, val x: Int) {
    operator fun plus(other: Point): Point {
        return Point(y + other.y, x + other.x)
    }
}

enum class Direction(val c: Char, val delta: Point) {
    UP('^', Point(-1, 0)),
    RIGHT('>', Point(0, 1)),
    DOWN('v', Point(1, 0)),
    LEFT('<', Point(0, -1));
}

class Task1 {

    companion object {

        val numericKeypad = buildMap {
            put('7', Point(0, 0)); put('8', Point(0, 1)); put('9', Point(0, 2))
            put('4', Point(1, 0)); put('5', Point(1, 1)); put('6', Point(1, 2))
            put('1', Point(2, 0)); put('2', Point(2, 1)); put('3', Point(2, 2))
                                         put('0', Point(3, 1)); put('A', Point(3, 2))
        }

        val directionalKeypad = buildMap {
                                         put('^', Point(0, 1)); put('A', Point(0, 2))
            put('<', Point(1, 0)); put('v', Point(1, 1)); put('>', Point(1, 2))
        }

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

        private fun cartesian(lists: List<Set<String>>): Set<List<String>> {
            if (lists.isEmpty()) return emptySet()  // Handle empty input

            return lists.fold(setOf(listOf())) { acc, set ->
                acc.flatMap { combination ->
                    set.map { element -> combination + element }
                }.toSet()
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
            val paths1 = keypadPaths(setOf(code), numericKeypad)
            val paths2 = keypadPaths(paths1, directionalKeypad)
            val paths3 = keypadPaths(paths2, directionalKeypad)
            return paths3.first()
        }

        private fun complexity(pad: String, seq: String) =
            pad.substringBefore('A').toInt() * seq.length

        fun solve(pads: List<String>): Int {
            return pads
                .sumOf { complexity(it, shortestPath(it)) }
        }
    }
}