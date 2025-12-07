package year_2025

class Day07(val input: String) {

    data class Point(val y: Int, val x: Int) {
        fun left() = copy(x = this.x - 1)
        fun right() = copy(x = this.x + 1)
        fun down() = copy(y = this.y + 1)
    }

    data class Area(val lines: List<String>) {

        fun findAll(symbol: Char) = lines
            .withIndex()
            .flatMap { (index, line) ->
                line.mapIndexedNotNull { x, c -> if (c == symbol) Point(index, x) else null }
            }
            .toSet()

        operator fun contains(p: Point) =
            p.y in lines.indices &&
            p.x in lines.first().indices
    }

    val map = Area(input.lines())

    val startPos = Point(0, map.lines.first().indexOf('S'))
    val splitters = map.findAll('^')

    fun solvePart1(): Int {
        var count = 0
        var beams = setOf(startPos)
        while (true) {
            val newBeams: Set<Point> = beams.flatMap { b ->
                val newB = b.down()
                if (newB in splitters) {
                    count++
                    listOf(newB.left(), newB.right())
                } else
                    listOf(newB)
            }.filter{ it in map }.toSet()
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
                val newB = b.down()
                if (newB in splitters) {
                    smartPut(newB.left(), c)
                    smartPut(newB.right(), c)
                } else
                    smartPut(newB, c)
            }
            newBeams.entries.removeIf { (p, _) -> p !in map }

            if (newBeams.isEmpty()) break
            beams = newBeams
        }
        return beams.values.sum()
    }
}