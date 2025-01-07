package year_2024.day_12

import year_2024.day_12.Direction.*

class Task2 {

    data class Line(val begin: Point, val end: Point)

    companion object {

        fun perimeter(r: Region): Long {
            val lines = r.genLines()
            return compact(lines).size.toLong()
        }

        private fun Region.genLines(): List<Line> {
            val result = mutableListOf<Line>()
            for (f in fields) {
                for (d in Direction.entries) {
                    val next = f.move(d)
                    if (next !in this) {
                        val begin = next
                        val end = when (d) {
                            UP, DOWN -> Point(next.y, next.x + 1)
                            LEFT, RIGHT -> Point(next.y + 1, next.x)
                        }
                        result += Line(begin, end)
                    }
                }
            }
            return result
        }

        private fun compact(borders: List<Line>): List<Line> {
            val result = borders.toMutableList()
            var wasCompacted: Boolean
            do {
                wasCompacted = false
                outer@ for (i in 0..<result.size - 1) {
                    val first = result[i]
                    for (j in i + 1 until result.size) {
                        val second = result[j]
                        if (first.end == second.begin && (first.begin.y == second.end.y || first.begin.x == second.end.x)) {
                            result[i] = Line(first.begin, second.end)
                            result.removeAt(j)
                            wasCompacted = true
                            break@outer
                        } else if (second.end == first.begin && (second.begin.y == first.end.y || second.begin.x == first.end.x)) {
                            result[i] = Line(second.begin, first.end)
                            result.removeAt(j)
                            wasCompacted = true
                            break@outer
                        }
                    }
                }
            } while (wasCompacted)
            return result
        }

        fun solve(map: List<List<Char>>): Long {
            val regions = findRegions(map)
            return regions
                .map { println("Region ${it.type}: ${it.area()} * ${perimeter(it)} = ${it.area() * perimeter(it)}"); it }
                .sumOf { it.area() * perimeter(it) }
        }
    }
}