package year_2023.day_01

class Task2 {
    companion object {

        val digiMap = mapOf(
            "zero" to 0,
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9,
        )

        fun numbers(line: String) = buildList {
            for (i in line.indices) {
                if (line[i].isDigit()) {
                    add(line[i].digitToInt())
                } else {
                    for (e in digiMap.entries) {
                        if (line.startsWith(e.key, i)) {
                            add(e.value)
                            break
                        }
                    }
                }
            }
        }

        fun solve(input: String): Int {
            return input.lines()
                .sumOf { line ->
                    val nums = numbers(line)
                    val first = nums.first()
                    val last = nums.last()
                    first * 10 + last
                }
        }
    }
}