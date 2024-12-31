package year_2024.day_02

import kotlin.math.abs
import kotlin.math.sign


class Task2 {
    companion object {

        fun solve(paths: List<List<Int>>): Int {
            return paths.count { isSafe(it) }
        }

        private fun isSafe(path: List<Int>): Boolean {
            if (!isSafeCheck(path)) {
                val mpath = path.toMutableList()
                for (i in path.indices) {
                    val v = mpath.removeAt(i)
                    if (isSafeCheck(mpath)) return true
                    mpath.add(i, v)
                }
                return false
            }
            else
                return true
        }

        private fun haveSameSign(first: Int, second: Int) = first.sign == second.sign

        private fun isSafeStep(step: Int) = abs(step) in 1..3

        private fun isSafeCheck(path: List<Int>): Boolean {
            return path
                .zipWithNext()
                .map { it.second - it.first }
                .zipWithNext()
                .all { isSafeStep(it.first) && haveSameSign(it.first, it.second) && isSafeStep(it.second) }
        }
    }
}