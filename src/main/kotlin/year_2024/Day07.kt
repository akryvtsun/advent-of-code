package year_2024

class Day07(private var input: String) {

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

    enum class Operation{
        PLUS {
            override fun apply(arg1: Long, arg2: Long) = arg1 + arg2
        },
        MULTIPLY {
            override fun apply(arg1: Long, arg2: Long) = arg1 * arg2
        };

        abstract fun apply(arg1: Long, arg2: Long): Long
    }

    fun solvePart1(): Long {
        return data
            .filter { isTrue(it) }
            .sumOf { it.test }
    }

    private fun isTrue(equation: Equation): Boolean {
        val opsPermutations = generateOps(equation.nums.size-1)
        return opsPermutations.any { operations ->
            equation.nums.reduceIndexed {
                    index, acc, value -> operations[index-1].apply(acc, value)
            } == equation.test
        }
    }

    private fun generateOps(size: Int) : Sequence<List<Operation>> = sequence {
        for (operation in Operation.entries) {
            if (size == 1) {
                yield(listOf(operation))
            }
            else {
                for (permutation in generateOps(size-1)) {
                    yield(listOf(operation) + permutation)
                }
            }
        }
    }

    enum class Operation2{
        PLUS {
            override fun apply(arg1: Long, arg2: Long) = arg1 + arg2
        },
        MULTIPLY {
            override fun apply(arg1: Long, arg2: Long) = arg1 * arg2
        },
        CONCAT {
            override fun apply(arg1: Long, arg2: Long) = "$arg1$arg2".toLong()
        };

        abstract fun apply(arg1: Long, arg2: Long): Long
    }

    fun solvePart2(): Long {
        return data
            .filter { isTrue2(it) }
            .sumOf { it.test }
    }

    private fun isTrue2(equation: Equation): Boolean {
        val opsPermutations = generateOps2(equation.nums.size-1)
        return opsPermutations.any { operations ->
            equation.nums.reduceIndexed {
                    index, acc, value -> operations[index-1].apply(acc, value)
            } == equation.test
        }
    }

    private fun generateOps2(side: Int) : Sequence<List<Operation2>> = sequence {
        for (operation in Operation2.entries) {
            if (side == 1) {
                yield(listOf(operation))
            }
            else {
                for (permutation in generateOps2(side-1)) {
                    yield(listOf(operation) + permutation)
                }
            }
        }
    }
}