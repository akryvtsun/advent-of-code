package year_2025

typealias Surface = List<String>

class Day04(input: String) {

    private val map: Surface = input.lines()

    data class Point(val y: Int, val x: Int)

    fun Surface.rolls() = buildSet {
        for (y in this@rolls.indices) {
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
        val rolls = map.rolls()
        return rolls.count { rolls.isAccessible(it) }
    }

    fun solvePart2(): Int {
        tailrec fun count(rolls: Set<Point>, acc: Int = 0): Int {
            val accessible = rolls.filter { rolls.isAccessible(it) }.toSet()
            return if (accessible.isEmpty()) {
                acc
            } else {
                count(rolls - accessible, acc + accessible.size)
            }
        }

        return count(map.rolls())
    }
}

