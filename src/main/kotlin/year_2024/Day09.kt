package year_2024

import java.util.Collections

class Day09(private val input: String) {

    companion object {
        const val SPACE = -1
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

    fun calcSum(data: List<Int>): Long =
        data.foldIndexed(0L) { i, acc, num ->
            val value = if (num == SPACE) 0 else num
            acc + i * value
        }

    fun solvePart1(): Long {
        val viz = makeViz(input)
        val packRez = pack(viz)
        val checksum = calcSum(packRez)
        return checksum
    }

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

    fun solvePart2(): Long {
        val viz = makeViz(input)
        val packRez = pack2(viz)
        val checksum = calcSum(packRez)
        return checksum
    }

    fun pack2(data: List<Int>): List<Int> {
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
}