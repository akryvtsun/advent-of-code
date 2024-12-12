package day_12

import day_12.Direction.*

class Task2 {

    data class Region(val type: Char, val fields: Set<Point>) {
        fun area() = fields.size

        fun perimeter(): Long {
            val lines = genLines()
            return compact(lines).size.toLong()
        }

        fun contains(p: Point) = this.fields.contains(p)

        fun genLines(): List<Line> {
            val result = mutableListOf<Line>()
            for (f in fields) {
                for (d in Direction.entries) {
                    val next = f.move(d)
                    if (!fields.contains(next)) {
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
    }

    data class Line(val begin: Point, val end: Point)

    companion object {

        fun solve(map: List<List<Char>>): Long {
            val regions = mutableSetOf<Region>()

            fun findRegion(type: Char, point: Point): Region {
                val visited = mutableSetOf(point)

                fun findRegionImpl(cur: Point) {
                    Direction.entries
                        .map { cur.move(it) }
                        .filter { it.y in map.indices && it.x in map.first().indices }
                        .filter { !visited.contains(it) }
                        .filter { map[it.y][it.x] == type }
                        .forEach { visited += it; findRegionImpl(it) }
                }

                findRegionImpl(point)
                return Region(type, visited)
            }

            for (y in map.indices) {
                for (x in map.first().indices) {
                    val cur = Point(y, x)
                    val type = map[y][x]
                    if (regions.none { it.contains(cur) }) {
                        regions += findRegion(type, cur)
                    }
                }
            }
            return regions.sumOf { it.area() * it.perimeter() }
        }
    }
}