package year_2019

import AocDay
import kotlin.math.floor

class Day01(input: String): AocDay<Int, Int>(input) {

    val masses = input.lines().map(String::toInt)

    fun fuel(mass: Int) = floor(mass / 3.0).toInt() - 2

    override fun solvePart1(): Int {
        return masses.sumOf { fuel(it) }
    }

    override fun solvePart2(): Int {
        fun mass(v: Int): Int {
            val m = fuel(v)
            if (m <= 0) return 0
            return m + mass(m)
        }

        return masses.sumOf { mass(it) }
    }
}

