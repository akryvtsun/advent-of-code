package year_2024

class Day05(private val input: String) {

    fun transform(input: String): Pair<List<Pair<Int, Int>>, List<List<Int>>> {
        val rules = mutableListOf<Pair<Int, Int>>()
        val updates = mutableListOf<List<Int>>()
        input.split("\n").forEach { line ->
            if (line.contains('|'))
                rules += readRule(line)
            else if (line.contains(','))
                updates += readUpdate(line)
        }
        return rules to updates
    }

    private fun readRule(line: String): Pair<Int, Int> {
        val first = line.substringBefore('|')
        val second = line.substringAfter('|')
        return first.toInt() to second.toInt()
    }

    private fun readUpdate(line: String): List<Int> {
        return """\d+""".toRegex().findAll(line).map { it.value.toInt() }.toList()
    }

    fun makePairs(left: Int, right: List<Int>) = right.map { left to it }

    fun isCorrect(rules: List<Pair<Int, Int>>, update: List<Int>): Boolean {
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

    fun findMiddle(update: List<Int>): Int = update[update.size/2]

    fun solvePart1(): Int {
        val (rules, updates) = transform(input)
        return updates
            .filter { isCorrect(rules, it) }
            .sumOf { findMiddle(it) }
    }

    fun solvePart2(): Int {
        val (rules, updates) = transform(input)

        fun makeChange(rules: List<Pair<Int, Int>>, mutable: MutableList<Int>): Boolean {
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

        fun correctOrder(rules: List<Pair<Int, Int>>, update: List<Int>): List<Int> {
            val mutable = update.toMutableList()
            while (true) {
                val wasChanged = makeChange(rules, mutable)
                if (!wasChanged)
                    return mutable
            }
        }

        return updates
            .filter { !isCorrect(rules, it) }
            .map { correctOrder(rules, it) }
            .sumOf { findMiddle(it) }
    }
}