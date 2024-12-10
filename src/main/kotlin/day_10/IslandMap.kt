package day_10

const val BLOCK = -1

data class IslandMap(private val map: List<List<Int>>) {

    companion object {
        fun transform(input: String): IslandMap {
            val lines = input.split("\n")
            val rows = lines.map { line ->
                line.map { if (it == '.') BLOCK else it.digitToInt() }
            }
            return IslandMap(rows)
        }
    }

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

