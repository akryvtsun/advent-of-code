package year_2025

import AocDay

class Day03(input: String) : AocDay<Int, Long>(input) {

    private val banks = input.lines()

    override fun solvePart1() = banks.sumOf {
        findMaxJoltage(it, 2).toInt()
    }

    override fun solvePart2() = banks.sumOf {
        findMaxJoltage(it, 12).toLong()
    }

    private tailrec fun findMaxJoltage(bank: String, count: Int, acc: String = ""): String =
        if (count == 1) {
            acc + bank.max()
        } else {
            val window = bank.dropLast(count - 1)
            val maxDigit = window.withIndex().maxBy { it.value }
            findMaxJoltage(bank.drop(maxDigit.index + 1), count - 1, acc + maxDigit.value)
        }
}

