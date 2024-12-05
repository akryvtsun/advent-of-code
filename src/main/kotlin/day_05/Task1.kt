package day_05

class Task1 {

    companion object {

        fun solve(rules: List<Pair<Int, Int>>, updates: List<List<Int>>): Int {
            return updates
                .filter { isCorrect(rules, it) }
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

        private fun makePairs(left: Int, right: List<Int>) = right.map { left to it }

        private fun findMiddle(update: List<Int>): Int = update[update.size/2]
    }
}