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

        fun keypadPath(code: String, keypad: Map<Char, Point>): String {
            return "A$code"
                .zipWithNext()
                .map { (start, stop) ->
                    val begin = keypad[start]!!
                    val end = keypad[stop]!!

                    val queue = PriorityQueue<Pair<String, Point>>(compareBy { it.first.length })
                    queue.add("" to begin)

                    while (true) {
                        val (path, curPos) = queue.remove()
                        if (curPos == end) return@map "${path}A"
                        val next = Direction.entries
                            .map { path + it.c to curPos + it.delta }
                            .filter { (_, nextPos) -> keypad.getKey(nextPos) != null }
                        queue.addAll(next)
                    }
                }
                .joinToString(separator = "")
        }

        fun shortestPath(code: String): String {
            println("code=$code")
            val path1 = keypadPath(code, numericKeypad).also { println("1. $it=${it.length} A=${it.count { it == 'A' }}") }
            val path2 = keypadPath(path1, directionalKeypad).also { println("2. $it=${it.length} A=${it.count { it == 'A' }}") }
            val path3 = keypadPath(path2, directionalKeypad).also { println("3. $it=${it.length} A=${it.count { it == 'A' }}") }
            return path3
        }

        private fun complexity(pad: String, seq: String) =
            pad.substringBefore('A').toInt() * seq.length

        fun solve(pads: List<String>): Int {
            return pads
                .sumOf { complexity(it, shortestPath(it)) }
        }
    }
}