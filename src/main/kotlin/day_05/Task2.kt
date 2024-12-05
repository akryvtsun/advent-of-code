package day_05

class Task2 {

    companion object {

        fun solve(rules: List<Pair<Int, Int>>, updates: List<List<Int>>): Int {
            return updates
                .filter { !isCorrect(rules, it) }
                .map { correctOrder(rules, it) }
                .sumOf { findMiddle(it) }
        }

        private fun isCorrect(rules: List<Pair<Int, Int>>, update: List<Int>): Boolean {
            return update
                .flatMapIndexed { i, n ->
                    if (i < update.size-1)
                        makePairs(n, update.subList(i+1, update.size))
                    else
                        listOf(i to i)
                }
                .all {
                    if (it.first == it.second) true
                    else it in rules
                }
        }

        private fun correctOrder(rules: List<Pair<Int, Int>>, update: List<Int>): List<Int> {
            val mutable = update.toMutableList()
            while (true) {
                val wasChanged = makeChange(rules, mutable)
                if (!wasChanged)
                    return mutable
            }
        }

        private fun makeChange(rules: List<Pair<Int, Int>>, mutable: MutableList<Int>): Boolean {
            for (i in 0 until mutable.size - 1) {
                for (j in i + 1 until mutable.size) {
                    if (Pair(mutable[i], mutable[j]) !in rules) {
                        val temp = mutable[j]
                        mutable[j] = mutable[i]
                        mutable[i] = temp
                        return true
                    }
                }
            }
            return false
        }

        private fun makePairs(left: Int, right: List<Int>) = right.map { left to it }

        private fun findMiddle(update: List<Int>): Int = update[update.size/2]
    }
}