package day_04

typealias Board = List<String>

class Task1 {

    companion object {

        fun solve(data: Board): Int {
            var sum = 0
            for (y in data.indices) {
                for (x in data.first().indices) {
                    sum += data.patternsCount(y, x)
                }
            }
            return sum
        }

        const val XMAS = "XMAS"

        data class Step(val dy: Int, val dx: Int)

        data class Pattern(private val steps: List<Step>) {
            fun match(data: Board, y: Int, x: Int) : Boolean {
                try {
                    var match = true
                    for (i in steps.indices) {
                        val step = steps[i]
                        val ry = y + step.dy
                        val rx = x + step.dx
                        match = match && data[ry][rx] == XMAS[i]
                    }
                    return match
                } catch (e: Exception) {
                    return false
                }
            }
        }

        private val PATTERNS = listOf<Pattern>(
            Pattern(listOf(Step(0,0), Step( 0, 1), Step( 0,  2), Step( 0,  3))),   // ->
            Pattern(listOf(Step(0,0), Step( 0,-1), Step( 0, -2), Step( 0, -3))),   // <-
            Pattern(listOf(Step(0,0), Step( 1, 0), Step( 2,  0), Step( 3,  0))),   // V
            Pattern(listOf(Step(0,0), Step(-1, 0), Step(-2,  0), Step(-3,  0))),   // /\
            Pattern(listOf(Step(0,0), Step( 1,-1), Step( 2, -2), Step( 3, -3))),   // V<
            Pattern(listOf(Step(0,0), Step( 1, 1), Step( 2,  2), Step( 3,  3))),   // V>
            Pattern(listOf(Step(0,0), Step(-1,-1), Step(-2, -2), Step(-3, -3))),   // /\<
            Pattern(listOf(Step(0,0), Step(-1, 1), Step(-2,  2), Step(-3,  3))),   // /\>
        )

        private fun Board.patternsCount(y: Int, x: Int): Int {
            return PATTERNS.count { it.match(this, y, x)  }
        }
    }
}