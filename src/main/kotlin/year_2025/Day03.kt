package year_2025

class Day03(private val input: String) {

    fun solvePart1(): Int {
        val banks = input.lines()
        return banks.sumOf { bank ->
            val bats = sequence {
                for (f in 0 until (bank.length - 1)) {
                    for (s in f + 1 until bank.length) {
                        val joltage = (bank[f].toString() + bank[s].toString()).toInt()
                        yield(joltage)
                    }
                }
            }
            bats.max()
        }
    }

    fun solvePart2(): Long {
        val banks = input.lines()
        return banks.sumOf { bank ->
//            var tempBank = bank
//            buildString {
//                repeat(12) { i ->
//                    val count = 12 - i
//                    val window = if (count == 1) tempBank else tempBank.take(tempBank.length - count + 1)
//                    val maxDigit = window.withIndex().maxBy { it.value }
//                    append(maxDigit.value)
//                    tempBank = tempBank.drop(maxDigit.index + 1)
//                }
//            }.toLong()
            findMaxJoltage(12, bank).toLong()
        }
    }

    private tailrec fun findMaxJoltage(count: Int, bank: String): String =
        if (count == 1) {
            bank.max().toString()
        } else {
            val window = bank.take(bank.length - count + 1)
            val maxDigit = window.withIndex().maxBy { it.value }
            maxDigit.value + findMaxJoltage(count - 1, bank.drop(maxDigit.index + 1))
        }
}

