package year_2024.day_12

enum class Direction(val dy: Int, val dx: Int) {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1)
}

data class Point(val y: Int, val x: Int) {
    operator fun plus(other: Point) = Point(y + other.y, x + other.x)

    fun move(dir: Direction) = Point(y + dir.dy, x + dir.dx)
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
                .map { cur.move(it) }
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