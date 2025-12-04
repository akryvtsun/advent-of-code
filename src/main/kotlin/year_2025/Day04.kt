package year_2025

typealias Surface = List<String>

class Day04(input: String) {

    private val map: Surface = input.lines()

    data class Point(val y: Int, val x: Int)

    fun Surface.rolls() = sequence {
        for (y in map.indices) {
            for (x in this@rolls[y].indices) {
                if (this@rolls[y][x] == '@') {
                    yield(Point(y, x))
                }
            }
        }
    }

    fun Surface.isAccessible(candidate: Point): Boolean {
        val places = sequence {
            for (y in candidate.y - 1..candidate.y + 1) {
                for (x in candidate.x - 1..candidate.x + 1) {
                    if (y in this@isAccessible.indices && x in this@isAccessible[y].indices) {
                        yield(this@isAccessible[y][x])
                    }
                }
            }
        }
        return places.count { it == '@' } - 1 < 4
    }

    fun solvePart1(): Int {
        return map.rolls().count { map.isAccessible(it) }
    }

    fun solvePart2(): Int {
        var count = 0
        var temp = map
        while (true) {
            val temp2 = temp.toMutableList()
            val acc = temp.rolls().count {
                val isAcc = temp.isAccessible(it)
                if (isAcc) {
                    val sb = StringBuilder(temp2[it.y])
                    sb.setCharAt(it.x, '.')
                    temp2[it.y] = sb.toString()
                }
                isAcc
            }
            println(acc)
            if (acc == 0) break
            count += acc
            temp = temp2
        }
        return count
    }
}

