package day_03


class Task1 {
    companion object {

        fun solve(data: String): Int {
            var pattern = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()

            var sum = 0
            var match = pattern.find(data)
            while (match != null) {
                check(match.groupValues.size == 3)
                val number1 = match.groupValues[1].toInt()
                val number2 = match.groupValues[2].toInt()
                sum += number1 * number2
                match = match.next()
            }
            return sum
        }
    }
}