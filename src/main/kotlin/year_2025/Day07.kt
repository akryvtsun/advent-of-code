package year_2025

class Day07(val input: String) {

    data class Point(val y: Int, val x: Int) {
        fun left() = copy(x = this.x - 1)
        fun right() = copy(x = this.x + 1)
        fun down() = copy(y = this.y + 1)
    }

    data class Surface(val lines: List<String>) {

        fun find(symbol: Char) = findAll(symbol).first()

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

    val area = Surface(input.lines())

    val startPos = area.find('S')
    val splitters = area.findAll('^')

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
            }.filter{ it in area }.toSet()
            if (newBeams.isEmpty()) break
            beams = newBeams
        }
        return count
    }

    fun solvePart2(): Long {
        var beams = mapOf(startPos to 1L)
        while (true) {
            val newBeams = mutableMapOf<Point, Long>()

            beams.flatMap { (b, c) ->
                val newB = b.down()
                if (newB in splitters) {
                    listOf(newB.left() to c, newB.right() to c)
                } else
                    listOf(newB to c)
            }
                .filter { (p, _) -> p in area }
                .forEach { (p, c) ->
                    newBeams.compute(p) { _, v -> v?.plus(c) ?: c }
                }

            if (newBeams.isEmpty()) break
            beams = newBeams
        }
        return beams.values.sum()
    }
}