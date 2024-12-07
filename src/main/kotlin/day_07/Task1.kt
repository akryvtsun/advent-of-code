package day_07

data class Equation(val test: Long, val nums: List<Long>)

class Task1 {

    enum class Operation{
        PLUS {
            override fun apply(arg1: Long, arg2: Long) = arg1 + arg2
        },
        MULTIPLY {
            override fun apply(arg1: Long, arg2: Long) = arg1 * arg2
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

//  Not optimal generation on Lists
//        private fun generateOps(side: Int) : List<List<Operation>> {
//            val permutations = mutableListOf<List<Operation>>()
//            for (operation in Operation.entries) {
//                if (side == 1) {
//                    permutations.add(listOf(operation))
//                }
//                else {
//                    for (permutation in generateOps(side-1)) {
//                        permutations.add(listOf(operation) + permutation)
//                    }
//                }
//            }
//            return permutations
//        }

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