package day_09

class Task2 {

    companion object {

        const val SPACE = -1

        fun solve(input: String): Long {
            val viz = makeViz(input)
            val packRez = pack(viz)
            val checksum = calcSum(packRez)
            return checksum
        }

        fun makeViz(data: String): List<Int> {
            var viz = mutableListOf<Int>()
            var id = 0
            data.forEachIndexed { i, c ->
                val type = if (isFile(i)) id++ else SPACE
                val size = c.digitToInt()
                viz += List(size) { type }
            }
            return viz
        }

        private fun isFile(pos: Int): Boolean = pos % 2 == 0

        fun pack(data: List<Int>): List<Int> {
            var begin = 0
            var end = data.size-1
            val buf = data.toMutableList()
            while (true) {
                while (buf[begin] != SPACE) begin++
                while (buf[end] == SPACE) end--
                if (begin >= end) break
                buf[begin] = buf[end]
                buf[end] = SPACE
            }
            return buf
        }

        fun calcSum(data: List<Int>): Long {
            var sum = 0L
            data.forEachIndexed { i, num ->
                val value = if (num == SPACE) 0 else num
                sum += i * value
            }
            return sum
        }
    }
}