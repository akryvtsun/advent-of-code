package year_2024.day_03


class Task1 {
    companion object {

        fun solve(data: String): Int {
            val pattern = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()

            var sum = 0
            var match = pattern.find(data)
            while (match != null) {
                check(match.groupValues.size == 3)
                val (_, first, second) = match.groupValues
                sum += first.toInt() * second.toInt()
                match = match.next()
            }
            return sum
        }
    }
}