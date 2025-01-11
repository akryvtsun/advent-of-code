package year_2023.day_01


class Task2 {
    companion object {

        fun solve(input: String): Int {
            return input.lines()
                .map { line ->
                    val first = line.first { it.isDigit() }
                    val last = line.findLast { it.isDigit() }
                    "$first$last"
                }
                .sumOf { it.toInt() }
        }
    }
}