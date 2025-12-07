package year_2024

typealias LongOp = (Long, Long) -> Long

class Day07(input: String) {

    data class Equation(val test: Long, val nums: List<Long>)

    fun transform(input: String): List<Equation> {
        return input.lines()
            .map { line ->
                val test = line.substringBefore(':').toLong()
                val nums = line.substringAfter(": ").split(" ").map { it.toLong() }
                Equation(test, nums)
            }
    }

    val data = transform(input)

    fun solvePart1(): Long {
        val ops: List<LongOp> = listOf(Long::plus, Long::times)
        return data
            .filter { isTrue(it, ops) }
            .sumOf { it.test }
    }

    private fun isTrue(equation: Equation, ops: List<LongOp>): Boolean {
        val opsPermutations = generateOps(equation.nums.size-1, ops)
        return opsPermutations.any { operations ->
            equation.nums.reduceIndexed {
                    index, acc, value -> operations[index-1](acc, value)
            } == equation.test
        }
    }

    private fun generateOps(size: Int, ops: List<LongOp>) : Sequence<List<LongOp>> = sequence {
        for (operation in ops) {
            if (size == 1) {
                yield(listOf(operation))
            }
            else {
                for (permutation in generateOps(size-1, ops)) {
                    yield(listOf(operation) + permutation)
                }
            }
        }
    }

    fun concat(arg1: Long, arg2: Long) = "$arg1$arg2".toLong()

    fun solvePart2(): Long {
        val ops: List<LongOp> = listOf(Long::plus, Long::times, ::concat)
        return data
            .filter { isTrue(it, ops) }
            .sumOf { it.test }
    }
}