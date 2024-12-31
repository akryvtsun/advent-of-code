package year_2024.day_03


class Task2 {
    companion object {

        fun solve(input: String): Int {
            var sum = 0
            var doFlag = true
            var begin = 0

            fun tryExecuteDo(begin: Int, end: Int = input.length) {
                if (doFlag) {
                    sum += calc(input.substring(begin, end))
                }
            }

            for (i in input.indices) {
                val str = input.substring(i)
                when {
                    str.startsWith("don't()") -> {
                        tryExecuteDo(begin, i)
                        begin = i
                        doFlag = false
                    }
                    str.startsWith("do()") -> {
                        tryExecuteDo(begin, i)
                        begin = i
                        doFlag = true
                    }
                }
            }
            tryExecuteDo(begin)
            return sum
        }

        val pattern = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()

        private fun calc(data: String): Int {
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