package year_2025

import AocDay

class Day04(input: String) : AocDay<Int, Int>(input) {

    private val area = Surface(input.lines())

    fun Surface.rolls() = findAll('@')

    fun Set<Point>.isAccessible(candidate: Point): Boolean {
        val places = sequence {
            for (y in candidate.y - 1..candidate.y + 1) {
                for (x in candidate.x - 1..candidate.x + 1) {
                    val neighbor = Point(y, x)
                    if (neighbor in area)
                        yield(neighbor)
                }
            }
        }
        return places.count { it in this } - 1 < 4
    }

    override fun solvePart1(): Int {
        val rolls = area.rolls()
        return rolls.count { rolls.isAccessible(it) }
    }

    override fun solvePart2(): Int {
        tailrec fun count(rolls: Set<Point>, acc: Int = 0): Int {
            val accessible = rolls.filter { rolls.isAccessible(it) }.toSet()
            return if (accessible.isEmpty()) {
                acc
            } else {
                count(rolls - accessible, acc + accessible.size)
            }
        }

        return count(area.rolls())
    }
}

