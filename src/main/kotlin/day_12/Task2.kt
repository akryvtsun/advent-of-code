package day_12

class Task2 {

    data class Point(val y: Int, val x: Int) {
        fun move(dir: Direction) = Point(y + dir.dy, x + dir.dx)
    }

    data class Region(val type: Char, val fields: Set<Point>) {
        fun area() = fields.size

        fun perimeter(): Long {
            return fields
                .flatMap { p -> Direction.entries.map { p.move(it) } }
                .count { !fields.contains(it) }
                .toLong()
        }

        fun contains(p: Point) = this.fields.contains(p)
    }

    enum class Direction(val dy: Int, val dx: Int) {
        UP(-1, 0),
        DOWN(1, 0),
        LEFT(0, -1),
        RIGHT(0, 1)
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