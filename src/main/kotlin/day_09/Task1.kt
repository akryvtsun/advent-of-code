package day_09

class Task1 {

    companion object {

        fun solve(input: String): Long {
            val viz = makeViz(input)
            //println(viz)
            val packRez = pack(viz)
            //println(packRez)
            val checksum = calcSum(packRez)
            return checksum
        }

        fun makeViz(data: String): String {
            var viz = StringBuilder()
            var id = 0
            data.forEachIndexed { i, c ->
                val type = if (isFile(i)) (id++).toString() else "."
                val size = c.digitToInt()
                viz.append(type.repeat(size))
            }
            return viz.toString()
        }

        private fun isFile(pos: Int): Boolean = pos % 2 == 0

        fun pack(data: String): String {
            var begin = 0
            var end = data.length-1
            val buf = StringBuilder(data)
            while (true) {
                while (buf[begin] in '0'..'9') begin++
                while (buf[end] == '.') end--
                if (begin >= end) break
                buf[begin] = buf[end]
                buf[end] = '.'
            }
            return buf.toString()
        }

        fun calcSum(data: String): Long {
            var sum = 0L
            data.forEachIndexed { i, c ->
                val digit = if (c == '.') 0 else c.digitToInt()
                sum += i * digit
            }
            return sum
        }
    }
}