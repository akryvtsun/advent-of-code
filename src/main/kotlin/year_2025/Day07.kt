package year_2025

class Day07(val input: String) {

    val area = Surface(input.lines())

    val startPoint = area.find('S')
    val splitters = area.findAll('^')

    fun solvePart1(): Int {
        var count = 0
        var beams = setOf(startPoint)
        while (true) {
            val newBeams: Set<Point> = beams.flatMap { b ->
                val newB = b.down()
                if (newB in splitters) {
                    count++
                    listOf(newB.left(), newB.right())
                } else
                    listOf(newB)
            }
                .filter { it in area }
                .toSet()
            if (newBeams.isEmpty()) break
            beams = newBeams
        }
        return count
    }

    fun solvePart2(): Long {
        var beams = mapOf(startPoint to 1L)
        while (true) {
            val newBeams = mutableMapOf<Point, Long>()

            beams.flatMap { (b, c) ->
                val newB = b.down()
                val targets = if (newB in splitters) {
                    listOf(newB.left(), newB.right())
                } else
                    listOf(newB)
                targets.map { it to c }
            }
                .filter { (point, _) -> point in area }
                .forEach { (point, count) ->
                    newBeams.compute(point) { _, v -> v?.plus(count) ?: count }
                }

            if (newBeams.isEmpty()) break
            beams = newBeams
        }
        return beams.values.sum()
    }
}