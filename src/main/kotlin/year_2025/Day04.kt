package year_2025

class Day04(input: String) {

    private val map = input.lines()

    data class Point(val y: Int, val x: Int)

    private val rolls = sequence {
        for (y in map.indices) {
            for (x in map[y].indices) {
                if (map[y][x] == '@') {
                    yield(Point(y, x))
                }
            }
        }
    }

    private fun isAccessible(candidate: Point): Boolean {
        val places = sequence {
            for (y in candidate.y - 1..candidate.y + 1) {
                for (x in candidate.x - 1..candidate.x + 1) {
                    if (y in map.indices && x in map[y].indices) {
                        yield(map[y][x])
                    }
                }
            }
        }
        return places.count { it == '@' } - 1 < 4
    }

    fun solvePart1(): Int {
        return rolls.count { isAccessible(it) }
    }

    fun solvePart2(): Int {
        return rolls.count { isAccessible(it) }
    }
}

