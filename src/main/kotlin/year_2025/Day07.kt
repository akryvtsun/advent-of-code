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

    fun inInMap(p: Point) = (p.y in 0..map.size) && (p.x in 0 .. map.first().length)

    fun solvePart1(): Int {
        var count = 0
        var beams = setOf(startPos)
        while (true) {
            val newBeams: Set<Point> = beams.flatMap { b ->
                val newB = Point(b.y + 1, b.x)
                if (newB in splitters) {
                    count++
                    listOf(Point(newB.y, newB.x - 1), Point(newB.y, newB.x + 1))
                }
                else
                    listOf(newB)
            }.filter(::inInMap).toSet()
            if (newBeams.isEmpty()) break
            beams = newBeams
        }
        return count
    }

    fun solvePart2(): Int {
        var paths = setOf(listOf(startPos))
        while (true) {
            val newPaths: Set<List<Point>> = paths.flatMap { p ->
                val l = p.last()
                val newL = l.copy(y = l.y + 1)
                if (newL in splitters) {
                    listOf(p + newL.copy(x = newL.x - 1), p + newL.copy(x = newL.x + 1))
                }
                else
                    listOf(p + newL)
            }.filter { inInMap(it.last()) }
            .toSet()
            if (newPaths.isEmpty()) break
            paths = newPaths
        }
        return paths.size
    }
}