package day_10

class Task1 {

    data class IslandMap(private val map: List<List<Int>>) {
        operator fun get(p: Point) = map[p.y][p.x]

        fun height() = map.size
        fun width() = map[0].size

        fun isStart(p: Point) = this[p] == 0
        fun isEnd(p: Point) = this[p] == 9

        fun isInMap(p: Point) =
            p.y in 0 until height() &&
            p.x in 0 until width() &&
            this[p] != -1

        fun getScore(p: Point): Int {
            val riched = mutableSetOf<Point>()
            getScoreImpl(p, riched)
            return riched.size
        }

        private fun getScoreImpl(p: Point, riched: MutableSet<Point>) {
            if (isEnd(p)) {
                riched.add(p)
            }
            else {
                val newPs = Direction.entries
                    .map { p + it }
                    .filter { isInMap(it) }
                    .filter { this[it] - this[p] == 1 }
                newPs.forEach { getScoreImpl(it, riched) }
            }
        }
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

    companion object {

        fun solve(input: IslandMap): Int {
            return sequence {
                for (y in 0 until input.height()) {
                    for (x in 0 until input.width()) {
                        yield(Point(y, x))
                    }
                }
            }
                .filter { input.isStart(it) }
                .sumOf { input.getScore(it) }
        }

    }
}