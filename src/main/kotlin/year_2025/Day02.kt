package year_2025

class Day02(private val input: String) {

    fun solvePart1(): Long {
        fun isInvalid(n: Long): Boolean {
            val s = n.toString()
            if (s.length % 2 == 0) {
                val popolam = s.length / 2
                val first = s.take(popolam)
                val second = s.drop(popolam)
                return first == second
            }
            return false
        }

        return input.split(",")
            .sumOf {
                val (from, to) = it.split("-").map(String::toLong)
                (from..to).sumOf { if (isInvalid(it)) it else 0 }
            }
    }

    fun solvePart2(): Long {
        fun isInvalid(n: Long): Boolean {
            val s = n.toString()
            for (i in 2..(s.length)) {
                if (s.length % i == 0) {
                    val len = s.length / i
                    if (s.chunked(len).distinct().size == 1) return true
                }
            }
            return false
        }

        return input.split(",")
            .sumOf {
                val (from, to) = it.split("-").map(String::toLong)
                (from..to).sumOf { if (isInvalid(it)) it else 0 }
            }
    }
}

