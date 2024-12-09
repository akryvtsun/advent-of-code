package day_09

import java.util.Collections

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
            val buf = data.toMutableList()
            var right = buf.size-1
            while (right >= 0) {
                if (buf[right] != SPACE) {
                    val id = buf[right]
                    var fsize = 1
                    while (buf[--right] == id) {
                        fsize++
                        if (right == 0) return buf
                    }
                    val fbegin = right+1

                    var left = 0
                    while (left < right) {
                        if (buf[left] == SPACE) {
                            val sbegin = left
                            var ssize = 1
                            while (buf[++left] == SPACE) ssize++

                            if (ssize >= fsize) {
                                for (i in 0 until fsize) {
                                    Collections.swap(buf, sbegin+i, fbegin+i)
                                }
                                break
                            }
                        } else
                            left++
                    }
                } else
                    right--
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