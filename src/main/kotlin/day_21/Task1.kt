package day_21

class Task1 {

    companion object {

        private fun shortestPath(pad: String) = ""

        private fun complexity(pad: String, seq: String) =
            pad.substringBefore('A').toInt() * seq.length

        fun solve(pads: List<String>): Int {
            return pads
                .sumOf { complexity(it, shortestPath(it)) }
        }
    }
}