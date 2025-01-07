package year_2024.day_12

class Task1 {

    data class Region(val type: Char, val fields: Set<Point>) {
        fun area() = fields.size

        fun perimeter(): Long {
            return fields
                .flatMap { p -> Direction.entries.map { p.move(it) } }
                .count { it !in this}
                .toLong()
        }

        operator fun contains(p: Point) = fields.contains(p)
    }

    companion object {

        fun solve(map: List<List<Char>>): Long {
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
            return regions.sumOf { it.area() * it.perimeter() }
        }
    }
}