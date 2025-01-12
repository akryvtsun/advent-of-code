package year_2023.day_01

class Task2 {
    companion object {
        val PATTERN =
            """[0-9]|zero|one|two|three|four|five|six|seven|eight|nine""".toRegex()

        private fun toDigit(input: String) = when (input) {
            "one", "1" -> 1
            "two", "2" -> 2
            "three", "3" -> 3
            "four", "4" -> 4
            "five", "5" -> 5
            "six", "6" -> 6
            "seven", "7" -> 7
            "eight", "8" -> 8
            "nine", "9" -> 9
            "zero", "0" -> 0
            else -> error("Invalid digit: $input")
        }

        fun solve(input: String): Int {
            return input.lines()
                .map { line ->
                    val matches = PATTERN.findAll(line)
                    val first = matches.first().value
                    val last = matches.last().value
                    "${toDigit(first)}${toDigit(last)}"
                }
                .sumOf { it.toInt() }
        }
    }
}