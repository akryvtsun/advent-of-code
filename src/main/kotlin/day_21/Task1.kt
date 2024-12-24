package day_21

import kotlin.math.absoluteValue

class Task1 {

    companion object {

        private fun moveVis(delta: Int, negative: Char, positive: Char): String {
            val char = if (delta < 0) negative else if (delta > 0) positive else null
            return char?.toString()?.repeat(delta.absoluteValue) ?: ""
        }

        fun numKeypadPaths(pad: String): String {

            fun Char.effective() =
                when (this) {
                    '0' -> -1
                    'A' -> 0
                    else -> this.digitToInt()
                }

            return "A$pad"
                .map { it.effective() }
                .zipWithNext()
                .flatMap { (start, stop) ->
                    val d = stop - start
                    val dy = d / 3
                    val dx = d % 3
                    listOf(
                        moveVis(dy, 'v', '^'),
                        moveVis(dx, '<', '>'),
                        "A"
                    )
                }
                .joinToString(separator = "")
        }

        fun dirKeypadPaths(pad: String): String {

            val moveMap = buildMap {
                put(('A' to 'A'), "")
                put(('A' to '^'), "<")
                put(('A' to '>'), "v")
                put(('A' to 'v'), "<v")
                put(('A' to '<'), "<v<")

                put(('^' to 'A'), ">")
                put(('^' to '^'), "")
                put(('^' to '>'), ">v")
                put(('^' to 'v'), "v")
                put(('^' to '<'), "v<")

                put(('>' to 'A'), "^")
                put(('>' to '^'), "<^")
                put(('>' to '>'), "")
                put(('>' to 'v'), "<")
                put(('>' to '<'), "<<")

                put(('v' to 'A'), "^>")
                put(('v' to '^'), "^")
                put(('v' to '>'), ">")
                put(('v' to 'v'), "")
                put(('v' to '<'), "<")

                put(('<' to 'A'), ">>^")
                put(('<' to '^'), ">^")
                put(('<' to '>'), ">>")
                put(('<' to 'v'), ">")
                put(('<' to '<'), "")
            }

            return "A$pad"
                .zipWithNext()
                .map { (start, stop) -> "${moveMap[start to stop]!!}A" }
                .also { println(it) }
                .joinToString(separator = "")
        }

        fun shortestPath(pad: String): String {
            val path1 = numKeypadPaths(pad).also { println("$it=${it.length}") }
            val path2 = dirKeypadPaths(path1).also { println("$it=${it.length}") }
            return dirKeypadPaths(path2).also { println("$it=${it.length}") }
        }

        private fun complexity(pad: String, seq: String) =
            pad.substringBefore('A').toInt() * seq.length

        fun solve(pads: List<String>): Int {
            return pads
                .sumOf { complexity(it, shortestPath(it)) }
        }
    }
}