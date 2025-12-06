package year_2025

class Day06(val input: String) {

    data class Task(val op: Char, val data: List<String>)

    fun solveTask(task: Task): Long {
        val realData = task.data.map { it.trim().toLong() }
        return if (task.op == '+') realData.sum() else realData.reduce(Long::times)
    }

    fun solve(preprOp: (List<String>) -> List<String>): Long {
        val data = input.lines()
        val operators = data.last().withIndex().filter { it.value != ' ' }
        val operands = data.dropLast(1)
        val tasks: List<Task> = operators.zipWithNext()
            .map { (d1, d2) -> Task(d1.value, operands.map { it.substring(d1.index, d2.index - 1) }) } +
                Task(operators.last().value, operands.map { it.substring(operators.last().index) })
        val processed = tasks.map { Task(it.op, preprOp(it.data)) }
        return processed.sumOf { solveTask(it) }
    }

    fun noOp(data: List<String>): List<String> = data

    fun solvePart1(): Long = solve(::noOp)

    fun transposeOp(data: List<String>): List<String> {
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