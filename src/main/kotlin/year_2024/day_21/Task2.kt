package year_2024.day_21

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import year_2024.Point
import java.util.PriorityQueue
import java.util.concurrent.ConcurrentHashMap

typealias PathSet = List<List<Any>>

class Task2 {

    companion object {

        private fun findPaths(begin: Point, end: Point, keypad: Map<Char, Point>): List<String> {
            val paths = mutableListOf<String>()
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
                    .filter { (nextPos, _) -> keypad.containsValue(nextPos) }
                queue.addAll(next)
            }
        }

        val memo = ConcurrentHashMap<Pair<Char, Char>, List<String>>()

        private fun extendPath(code: String, keypad: Map<Char, Point>): PathSet {
            return "A$code"
                .zipWithNext()
                .map { move ->
                    memo.computeIfAbsent(move) { key ->
                        val begin = keypad[key.first]!!
                        val end = keypad[key.second]!!
                        findPaths(begin, end, keypad)
                    }
                }
        }

        // TODO keep only shortest paths and give away others
        suspend fun keypadPath(paths: PathSet, keypad: Map<Char, Point>): PathSet = coroutineScope {
            paths.map { segment ->
                async {
                    segment.map { element ->
                        when (element) {
                            is String -> extendPath(element, keypad)
                            is List<*> -> keypadPath(element as PathSet, keypad)
                            else -> throw IllegalStateException()
                        }
                    }
                }
            }.awaitAll()
        }

        fun shortestPathLength(code: String): Int =
            runBlocking(Dispatchers.Default) {
                var paths = keypadPath(listOf(listOf(code)), numericKeypad).first().first() as PathSet
                repeat(25) {
                    println(it)
                    paths = keypadPath(paths, directionalKeypad)
                }
                paths.length()
            }

        // TODO calc length only shortest paths?
        fun PathSet.length(): Int = sumOf { segment ->
            segment.sumOf { element ->
                when (element) {
                    is String -> element.length
                    is List<*> -> (element as PathSet).length()
                    else -> throw IllegalStateException()
                }
            }
        }

        private fun complexity(pad: String, minLength: Int) =
            pad.substringBefore('A').toInt() * minLength

        fun solve(pads: List<String>): Int {
            return pads.sumOf { complexity(it, shortestPathLength(it)) }
        }
    }
}