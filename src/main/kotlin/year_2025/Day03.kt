package year_2025

import java.math.BigInteger

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

    fun solvePart2(): BigInteger {
        val banks = input.lines()
        return banks.sumOf { bank ->
            var tempBank = bank
            buildString {
                repeat(12) { i ->
                    val count = 12 - i
                    val window = if (count == 1) tempBank else tempBank.take(tempBank.length - count + 1)
                    val d = window.max()
                    append(d)
                    tempBank = tempBank.drop(tempBank.indexOf(d) + 1)
                }
            }
                //.also { println(it) }
                .toBigInteger()
        }
    }
}

