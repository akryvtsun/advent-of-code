package day_02

import kotlin.math.abs
import kotlin.math.sign


class Task2 {
    companion object {

        fun solve(paths: List<List<Int>>): Int {
            return paths.count { isSafe(it) }
        }

        private fun isSafe(path: List<Int>): Boolean {
            val steps =  path.zipWithNext { first, second -> second - first }
            val unSafeCount = steps.count { !isSafeStep(it) }
            val isMonotonic = steps.zipWithNext().all { haveSameSign(it.first, it.second) }
            return isMonotonic && unSafeCount <= 1
        }

        private fun haveSameSign(first: Int, second: Int) = first.sign == second.sign

        private fun isSafeStep(step: Int) = abs(step) in 1..3
    }
}