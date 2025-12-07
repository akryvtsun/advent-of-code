package year_2025

class Day07(val input: String) {

    data class Point(val y: Int, val x: Int)

    val map = input.lines()

    val startPos = Point(0, map.first().indexOf('S'))
    val splitters: Set<Point> = map
        .withIndex()
        .flatMap { line ->
            line.value.mapIndexedNotNull { x, c -> if (c == '^') Point(line.index, x) else null }
        }.toSet()

    fun isInArea(p: Point) = (p.y in 0..map.size) && (p.x in 0..map.first().length)

    fun solvePart1(): Int {
        var count = 0
        var beams = setOf(startPos)
        while (true) {
            val newBeams: Set<Point> = beams.flatMap { b ->
                val newB = b.copy(y = b.y + 1)
                if (newB in splitters) {
                    count++
                    listOf(newB.copy(x = newB.x - 1), newB.copy(x = newB.x + 1))
                } else
                    listOf(newB)
            }.filter(::isInArea).toSet()
            if (newBeams.isEmpty()) break
            beams = newBeams
        }
        return count
    }

    fun solvePart2(): Long {
        var beams = mutableMapOf(startPos to 1L)
        while (true) {
            val newBeams = mutableMapOf<Point, Long>()

            fun smartPut(p: Point, count: Long) {
                val oldCount = newBeams[p]
                if (oldCount != null) {
                    newBeams[p] = oldCount + count
                } else {
                    newBeams[p] = count
                }
            }

            beams.forEach { (b, c) ->
                val newB = b.copy(y = b.y + 1)
                if (newB in splitters) {
                    smartPut(newB.copy(x = newB.x - 1), c)
                    smartPut(newB.copy(x = newB.x + 1), c)
                } else
                    smartPut(newB, c)
            }
            newBeams.entries.removeIf { (p, _) -> !isInArea(p) }

            if (newBeams.isEmpty()) break
            beams = newBeams
        }
        return beams.values.sum()
    }
}