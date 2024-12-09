package day_07

class Task2 {

    enum class Operation{
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

    companion object {

        fun solve(data: List<Equation>): Long {
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

        private fun generateOps(side: Int) : Sequence<List<Operation>> = sequence {
            for (operation in Operation.entries) {
                if (side == 1) {
                    yield(listOf(operation))
                }
                else {
                    for (permutation in generateOps(side-1)) {
                        yield(listOf(operation) + permutation)
                    }
                }
            }
        }
    }
}