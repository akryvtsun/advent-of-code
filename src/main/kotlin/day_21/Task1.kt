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
                put(('A' to 'A'), Pair(0, 0))
                put(('A' to '^'), Pair(0, -1))
                put(('A' to '>'), Pair(1, 0))
                put(('A' to 'v'), Pair(1, -1))
                put(('A' to '<'), Pair(1, -2))

                put(('^' to 'A'), Pair(0, 1))
                put(('^' to '^'), Pair(0, 0))
                put(('^' to '>'), Pair(1, 1))
                put(('^' to 'v'), Pair(1, 0))
                put(('^' to '<'), Pair(1, -1))

                put(('>' to 'A'), Pair(-1, 0))
                put(('>' to '^'), Pair(-1, -1))
                put(('>' to '>'), Pair(0, 0))
                put(('>' to 'v'), Pair(0, -1))
                put(('>' to '<'), Pair(0, -2))

                put(('v' to 'A'), Pair(-1, 1))
                put(('v' to '^'), Pair(-1, 0))
                put(('v' to '>'), Pair(0, 1))
                put(('v' to 'v'), Pair(0, 0))
                put(('v' to '<'), Pair(0, -1))

                put(('<' to 'A'), Pair(-1, 2))
                put(('<' to '^'), Pair(-1, 1))
                put(('<' to '>'), Pair(0, 2))
                put(('<' to 'v'), Pair(0, 1))
                put(('<' to '<'), Pair(0, 0))
            }
println(">>> pad="+pad)
            return "A$pad"
                .zipWithNext()
                .flatMap { (start, stop) ->
                    val (dy, dx) = moveMap[start to stop]!!
                    listOf(
                        moveVis(dy, 'v', '^'),
                        moveVis(dx, '<', '>'),
                        "A"
                    ).also { println(it) }
                }
                .joinToString(separator = "").also { println(">>>>> "+it) }
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