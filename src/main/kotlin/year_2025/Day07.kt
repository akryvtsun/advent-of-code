package year_2025

import UNKNOWN_VALUE

class Day07(val input: String) {

    data class Point(val y: Int, val x: Int)

    fun solvePart1(): Int {
        val map = input.lines()

        val startPos = Point(0, map.first().indexOf('S'))
        val splitters: Set<Point> = map
            .withIndex()
            .flatMap { line ->
                line.value.mapIndexedNotNull { x, c -> if (c == '^') Point(line.index, x) else null }
            }.toSet()

        fun inInMap(p: Point) = (p.y in 0..map.size) && (p.x in 0 .. map.first().length)

        var count = 0
        var beams = setOf(startPos)
        while (true) {
            val newPos: Set<Point> = beams.flatMap { b ->
                val newB = Point(b.y + 1, b.x)
                if (newB in splitters) {
                    count++
                    listOf(Point(newB.y, newB.x - 1), Point(newB.y, newB.x + 1))
                }
                else
                    listOf(newB)
            }.filter(::inInMap).toSet()
            if (newPos.isEmpty()) break
            beams = newPos
        }
        return count
    }

    fun solvePart2(): Int {
        return UNKNOWN_VALUE
    }
}