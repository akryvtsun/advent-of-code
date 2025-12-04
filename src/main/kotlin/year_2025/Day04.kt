package year_2025

typealias Surface = List<String>

class Day04(input: String) {

    private val map: Surface = input.lines()

    data class Point(val y: Int, val x: Int)

    fun Surface.rolls() = buildList {
        for (y in map.indices) {
            for (x in this@rolls[y].indices) {
                if (this@rolls[y][x] == '@') {
                    add(Point(y, x))
                }
            }
        }
    }

    fun Surface.isAccessible(candidate: Point, r: List<Point>): Boolean {
        val places = sequence {
            for (y in candidate.y - 1..candidate.y + 1) {
                for (x in candidate.x - 1..candidate.x + 1) {
                    if (y in this@isAccessible.indices && x in this@isAccessible[y].indices) {
                        yield(Point(y, x))
                    }
                }
            }
        }
        return places.count { it in r } - 1 < 4
    }

    fun solvePart1(): Int {
        val r = map.rolls()
        return r.count { map.isAccessible(it, r) }
    }

    fun solvePart2(): Int {
        var count = 0
        var temp = map.rolls()
        while (true) {
            val isAcc = temp.filter { map.isAccessible(it, temp) }
            if (isAcc.isEmpty()) break
            count += isAcc.size
            temp = temp - isAcc
        }
        return count
    }
}

