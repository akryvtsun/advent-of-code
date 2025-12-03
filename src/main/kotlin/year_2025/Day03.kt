package year_2025

import UNKNOWN_VALUE

class Day03(private val input: String) {

    fun solvePart1(): Int {
        val banks = input.lines()
        return banks.sumOf { bank ->
            val bats = sequence {
                for (f in 0 until (bank.length - 1)) {
                    for (s in f+1 until  bank.length) {
                        val joltage = (bank[f].toString() + bank[s].toString()).toInt()
                        yield(joltage)
                    }
                }
            }
            bats.max()
        }
    }

    fun solvePart2(): Int {
        return UNKNOWN_VALUE
    }
}

