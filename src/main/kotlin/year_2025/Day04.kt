package year_2025

typealias Surface = List<String>

class Day04(input: String) {

    private val map: Surface = input.lines()

    data class Point(val y: Int, val x: Int)

    fun Surface.rolls() = buildSet {
        for (y in map.indices) {
            for (x in this@rolls[y].indices) {
                if (this@rolls[y][x] == '@') {
                    add(Point(y, x))
                }
            }
        }
    }

    fun Set<Point>.isAccessible(candidate: Point): Boolean {
        val places = sequence {
            for (y in candidate.y - 1..candidate.y + 1) {
                for (x in candidate.x - 1..candidate.x + 1) {
                    if (y in map.indices && x in map[y].indices) {
                        yield(Point(y, x))
                    }
                }
            }
        }
        return places.count { it in this } - 1 < 4
    }

    fun solvePart1(): Int {
        val r = map.rolls()
        return r.count { r.isAccessible(it) }
    }

    fun solvePart2(): Int {
        var count = 0
        val temp = map.rolls().toMutableSet()
        while (true) {
            val isAcc = temp.filter { temp.isAccessible(it) }.toSet()
            if (isAcc.isEmpty()) break
            count += isAcc.size
            temp -= isAcc
        }
        return count
    }
}

