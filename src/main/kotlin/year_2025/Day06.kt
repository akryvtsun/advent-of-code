package year_2025

class Day06(val input: String) {

    fun solvePart1(): Long {
        var sum = 0L
        var data = input.lines()
        while (true) {
            val task = data.map { it.trimStart().substringBefore(" ") }
            data = data.map { (it.trimStart() + " ").substringAfter(" ") }
            val result = if (task.last() == "+")
                task.dropLast(1).sumOf { it.toLong() }
            else
                task.dropLast(1).map(String::toLong).reduce { a, b -> a * b }
            sum += result
            if (data.all { it.isBlank() }) break
        }
        return sum
    }

    fun solve2(task: Task): Long {
        val transposedData = task.data.fold(List(task.data.first().length) { "" }) { acc, value ->
            value.mapIndexed { index, c -> acc[index] + c }
        }
        val realData = transposedData.map { it.trim().toLong() }
        return if (task.op == '+') realData.sum() else realData.reduce { a, b -> a * b }
    }

    data class Task(val op: Char, val data: List<String>)

    fun solvePart2(): Long {
        var data = input.lines()
        val delims = data.last().withIndex().filter { (_, v) -> v != ' ' } + IndexedValue(data.last().length + 1, '.')
        val operands = data.dropLast(1)
        val tasks: List<Task> = delims.zipWithNext()
            .map { (d1, d2) -> Task(d1.value, operands.map { it.substring(d1.index, d2.index - 1) }) }
        return tasks.sumOf { solve2 (it) }
    }
}