package year_2025

class Day06(val input: String) {

    data class Task(val op: Char, val data: List<String>)

    fun solveTask(task: Task): Long {
        val realData = task.data.map { it.trim().toLong() }
        return if (task.op == '+') realData.sum() else realData.reduce { a, b -> a * b }
    }

    fun solve(preprOp: (List<String>) -> List<String>) : Long {
        val data = input.lines()
        val delims = data.last().withIndex().filter { (_, v) -> v != ' ' }
        val operands = data.dropLast(1)
        val tasks: List<Task> = delims.zipWithNext()
            .map { (d1, d2) -> Task(d1.value, operands.map { it.substring(d1.index, d2.index - 1) }) } +
                Task(delims.last().value, operands.map { it.substring(delims.last().index) })
        val processed = tasks.map { Task(it.op, preprOp(it.data)) }
        return processed.sumOf { solveTask(it) }
    }

    fun noOp(data: List<String>): List<String> = data

    fun solvePart1(): Long = solve(::noOp)

    fun transposeOp(data: List<String>): List<String> {
        val length = data.maxOf { it.length }
        return data.fold(List(length) { "" }) { acc, value ->
            value.mapIndexed { index, c -> acc[index] + c }
        }
    }

    fun solvePart2(): Long = solve(::transposeOp)
}