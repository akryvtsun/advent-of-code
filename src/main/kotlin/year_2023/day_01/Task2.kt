package year_2023.day_01

class Task2 {
    companion object {
        val PATTERN =
            """(0|1|2|3|4|5|6|7|8|9|one|two|three|four|five|six|seven|eight|nine|zero)""".toRegex()

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
                    var first: String? = null
                    var last: String? = null
                    for (m in PATTERN.findAll(line)) {
                        if (first == null) {
                            first = m.value
                            last = m.value
                        } else {
                            last = m.value
                        }
                    }
                    "${toDigit(first!!)}${toDigit(last!!)}"
                }
                .also { println(it) }
                .sumOf { it.toInt() }
        }
    }
}