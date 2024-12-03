package day_03


class Task2 {
    companion object {

        fun solve(input: String): Int {
            var sum = 0
            var doFlag = true
            var begin = 0
            for (i in input.indices) {
                when {
                    input.substring(i).startsWith("don't()") -> {
                        if (doFlag) {
                            sum += calc(input.substring(begin, i))
                        }
                        begin = i
                        doFlag = false
                    }
                    input.substring(i).startsWith("do()") -> {
                        if (doFlag) {
                            sum += calc(input.substring(begin, i))
                        }
                        begin = i
                        doFlag = true
                    }
                }
            }
            if (doFlag) {
                sum += calc(input.substring(begin))
            }
            return sum
        }

        private fun calc(data: String): Int {
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