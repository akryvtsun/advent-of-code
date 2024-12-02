package day_02

import kotlin.math.abs
import kotlin.math.sign


class Task2 {
    companion object {

        fun solve(paths: List<List<Int>>): Int {
            return paths.count { isSafe(it) }
        }

        private fun isSafe(path: List<Int>): Boolean {
            val mpath = path.toMutableList()
            for (i in (1 until mpath.size-1)) {
                // check left step
                if (!isSafeStep(mpath[i] - mpath[i - 1])) {
                    val v = mpath.removeAt(i - 1)
                    if (isSafeCheck(mpath)) return true
                    mpath.add(i - 1, v)
                    mpath.removeAt(i)
                    if (isSafeCheck(mpath)) return true
                    return false
                }
                // check right step
                if (!isSafeStep(mpath[i + 1] - mpath[i])) {
                    val v = mpath.removeAt(i)
                    if (isSafeCheck(mpath)) return true
                    mpath.add(i, v)
                    mpath.removeAt(i + 1)
                    if (isSafeCheck(mpath)) return true
                    return false
                }
                // check monotonic
                if (!haveSameSign(mpath[i] - mpath[i - 1], mpath[i + 1] - mpath[i])) {
                    val v = mpath.removeAt(i)
                    if (isSafeCheck(mpath)) return true
                    mpath.add(i, v)
                    mpath.removeAt(i + 1)
                    if (isSafeCheck(mpath)) return true
                    return false
                }
            }
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