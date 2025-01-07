package year_2024.day_12

import year_2024.Point

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