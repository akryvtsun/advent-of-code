package day_04


class Task2 {
    companion object {

        fun solve(data: Board): Int {
            var sum = 0
            for (y in data.indices) {
                for (x in data.first().indices) {
                    if (data.patternMatch(y, x)) sum++
                }
            }
            return sum
        }

        private fun Board.patternMatch(y: Int, x: Int): Boolean {
            try {
                var match = true
                match = match && this[y][x] == 'A'
                match = match && (
                        (this[y-1][x-1] == 'M' && this[y+1][x+1] == 'S') ||
                        (this[y-1][x-1] == 'S' && this[y+1][x+1] == 'M'))
                match = match && (
                        (this[y+1][x-1] == 'M' && this[y-1][x+1] == 'S') ||
                        (this[y+1][x-1] == 'S' && this[y-1][x+1] == 'M'))
                return match
            } catch (e: Exception) {
                return false
            }
        }
    }
}