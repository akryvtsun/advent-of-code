package year_2024

import AocDay
import kotlin.math.abs
import kotlin.math.sign

class Day02(input: String) : AocDay<Int, Int>(input) {

    private fun transform(input: String) =
        input.lines()
            .map { it.split(" ").map(String::toInt) }

    override fun solvePart1(): Int {
        val paths = transform(input)
        return paths.count { isSafe(it) }
    }

    private fun isSafe(path: List<Int>): Boolean {
        return path
            .zipWithNext()
            .map { it.second - it.first }
            .zipWithNext()
            .all { isSafeStep(it.first) && haveSameSign(it.first, it.second) && isSafeStep(it.second) }
    }

    private fun haveSameSign(first: Int, second: Int) = first.sign == second.sign

    private fun isSafeStep(step: Int) = abs(step) in 1..3

    override fun solvePart2(): Int {
        val paths = transform(input)
        return paths.count { isSafe2(it) }
    }

    private fun isSafe2(path: List<Int>): Boolean {
        if (!isSafe(path)) {
            val mpath = path.toMutableList()
            for (i in path.indices) {
                val v = mpath.removeAt(i)
                if (isSafe(mpath)) return true
                mpath.add(i, v)
            }
            return false
        }
        else
            return true
    }
}