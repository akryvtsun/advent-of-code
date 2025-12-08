package year_2024

class Day12(private val input: String) {

    enum class Direction(val delta: Point) {
        UP(Point(-1, 0)),
        DOWN(Point(1, 0)),
        LEFT(Point(0, -1)),
        RIGHT(Point(0, 1))
    }

    data class Region(val type: Char, val fields: Set<Point>) {
        fun area() = fields.size.toLong()

        operator fun contains(p: Point) = fields.contains(p)
    }

    fun findRegions(map: List<List<Char>>): Set<Region> {
        val regions = mutableSetOf<Region>()

        fun findRegion(type: Char, point: Point): Region {
            val visited = mutableSetOf(point)

            fun findRegionImpl(cur: Point) {
                Direction.entries
                    .map { cur + it.delta }
                    .filter { it.y in map.indices && it.x in map.first().indices }
                    .filter { it !in visited }
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
                if (regions.none { cur in it }) {
                    regions += findRegion(type, cur)
                }
            }
        }

        return regions
    }

    fun transform(input: String) = input.lines(). map { it.toList() }

    fun perimeter(r: Region): Long {
        return r.fields
            .flatMap { p -> Direction.entries.map { p + it.delta } }
            .count { it !in r}
            .toLong()
    }

    fun solvePart1(): Long {
        val map = transform(input)
        return findRegions(map).sumOf { it.area() * perimeter(it) }
    }

    fun solvePart2(): Long {
        val map = transform(input)
        return findRegions(map).sumOf { it.area() * perimeter2(it) }
    }

    data class Line(val begin: Point, val end: Point)

    fun perimeter2(r: Region): Long {
        val points = r.genBorders()
        return compact(points).size.toLong()
    }

    private fun Region.genBorders(): List<Pair<Line, Direction>> =
        buildList {
            for (field in fields) {
                for (dir in Direction.entries) {
                    val next = field + dir.delta
                    if (next !in this@genBorders) {
                        add(Line(field, field) to dir)
                    }
                }
            }
        }

    private fun compact(borders: List<Pair<Line, Direction>>): List<Pair<Line, Direction>> {
        val result = borders.toMutableList()
        var wasCompacted: Boolean
        do {
            wasCompacted = false
            outer@ for (i in 0..<result.size - 1) {
                val (p1, d1) = result[i]
                for (j in i + 1 until result.size) {
                    val (p2, d2) = result[j]
                    if (d1 == d2) {
                        when (d1) {
                            Direction.LEFT, Direction.RIGHT ->
                                if (p1.end + Point(1, 0) == p2.begin) {
                                    result[i] = Line(p1.begin, p2.end) to d1
                                    result.removeAt(j)
                                    wasCompacted = true
                                    break@outer
                                } else if (p2.end + Point(1, 0) == p1.begin) {
                                    result[j] = Line(p2.begin, p1.end) to d2
                                    result.removeAt(i)
                                    wasCompacted = true
                                    break@outer
                                }
                            Direction.UP, Direction.DOWN ->
                                if (p1.end + Point(0, 1) == p2.begin) {
                                    result[i] = Line(p1.begin, p2.end) to d1
                                    result.removeAt(j)
                                    wasCompacted = true
                                    break@outer
                                } else if (p2.end + Point(0, 1) == p1.begin) {
                                    result[j] = Line(p2.begin, p1.end) to d2
                                    result.removeAt(i)
                                    wasCompacted = true
                                    break@outer
                                }
                        }
                    }
                }
            }
        } while (wasCompacted)
        return result
    }
}