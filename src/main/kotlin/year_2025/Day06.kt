package year_2025

class Day06(val input: String) {

    data class Task(val op: Char, val data: List<String>) {

        fun solve(): Long {
            val operator: (Long, Long) -> Long  = if (op == '+') Long::plus else Long::times
            val numbers = data.map(String::trim).map(String::toLong)
            return numbers.reduce(operator)
        }
    }

    private fun solve(preprOp: (List<String>) -> List<String>): Long {
        val data = input.lines()
        val operators = data.last().withIndex().filter { it.value != ' ' }
        val operands = data.dropLast(1)
        val tasks: List<Task> = buildList {
            operators.zipWithNext()
                .map { (d1, d2) ->
                    add(
                        Task(d1.value, operands.map { it.substring(d1.index, d2.index - 1) })
                    )
                }
            // last task (goes to end of line)
            add(
                Task(operators.last().value, operands.map { it.substring(operators.last().index) })
            )
        }.map { task -> task.copy(data = preprOp(task.data)) }
        return tasks.sumOf { it.solve() }
    }

    private fun noOp(data: List<String>): List<String> = data

    fun solvePart1(): Long = solve(::noOp)

    private fun transposeOp(data: List<String>): List<String> {
        val length = data.maxOf { it.length }

        return (0 until length).map { col ->
            buildString {
                data.forEach { row ->
                    append(
                        if (col < row.length) row[col] else ' '
                    )
                }
            }
        }
    }

    fun solvePart2(): Long = solve(::transposeOp)
}