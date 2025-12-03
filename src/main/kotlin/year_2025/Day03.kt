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
            val bats = sequence {
                for (f in 0 until (bank.length - 2)) {
                    for (s in f + 1 until bank.length - 1) {
                        for (t in s + 1 until bank.length) {
                            val sb = StringBuilder(bank)
                            sb.deleteCharAt(f)
                            sb.deleteCharAt(s-1)
                            sb.deleteCharAt(t-2)
                            val joltage = sb.toString().toLong()
                            yield(joltage)
                        }
                    }
                }
            }
            bats.max()
        }
    }
}

