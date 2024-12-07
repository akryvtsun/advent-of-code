package day_07

class Task1 {

    data class Equation(val test: Long, val nums: List<Long>)

    companion object {

        fun solve(data: List<Equation>): Long {
            return data
                .filter { isTrue(it) }
                .sumOf { it.test }
        }

        private fun isTrue(equation: Equation): Boolean {
            return true
        }
    }
}