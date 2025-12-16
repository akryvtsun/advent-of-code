package year_2024

class Day10(input: String) {

    companion object {
        const val BLOCK = -1

        fun transform(input: String): IslandMap {
            val lines = input.lines()
            val rows = lines.map { line ->
                line.map { if (it == '.') BLOCK else it.digitToInt() }
            }
            return IslandMap(rows)
        }

        fun IslandMap.getScore(p: Point): Int {
            val riched = mutableSetOf<Point>()

            fun getScoreImpl(p: Point) {
                if (isEnd(p)) {
                    riched.add(p)
                }
                else {
                    val newPs = Direction.entries
                        .map { p + it }
                        .filter { isInMap(it) }
                        .filter { isRouteStep(p, it) }
                    newPs.forEach { getScoreImpl(it) }
                }
            }

            getScoreImpl(p)
            return riched.size
        }
    }

    data class IslandMap(private val map: List<List<Int>>) {

        operator fun get(p: Point) = map[p.y][p.x]

        fun height() = map.size
        fun width() = map[0].size

        fun isStart(p: Point) = this[p] == 0
        fun isEnd(p: Point) = this[p] == 9

        fun isRouteStep(current: Point, next: Point) = this[next] - this[current] == 1

        fun isInMap(p: Point) =
            p.y in 0 until height() &&
                    p.x in 0 until width() &&
                    this[p] != BLOCK
    }

    data class Point(val y: Int, val x: Int) {
        operator fun plus(d: Direction) = Point(this.y + d.y, this.x + d.x)
    }

    enum class Direction(val y: Int, val x: Int) {
        UP(-1, 0),
        DOWN(1, 0),
        LEFT(0, -1),
        RIGHT(0, 1)
    }

    val map = transform(input)

    fun solvePart1(): Int {
        return sequence {
            for (y in 0 until map.height()) {
                for (x in 0 until map.width()) {
                    yield(Point(y, x))
                }
            }
        }
            .filter { map.isStart(it) }
            .sumOf { map.getScore(it) }
    }

    fun solvePart2(): Int {
        return sequence {
            for (y in 0 until map.height()) {
                for (x in 0 until map.width()) {
                    yield(Point(y, x))
                }
            }
        }
            .filter { map.isStart(it) }
            .sumOf { map.getRate(it) }
    }

    fun IslandMap.getRate(p: Point): Int {
        val routs = mutableSetOf<List<Point>>()

        fun getRateImpl(p: Point, route: List<Point>) {
            val newRoute = route + p
            if (isEnd(p)) {
                routs += newRoute
            }
            else {
                val newPs = Direction.entries
                    .map { p + it }
                    .filter { isInMap(it) }
                    .filter { isRouteStep(p, it) }
                newPs.forEach { getRateImpl(it, newRoute) }
            }
        }

        getRateImpl(p, emptyList())
        return routs.size
    }
}