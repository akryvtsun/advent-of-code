package year_2024.day_12

enum class Direction(val dy: Int, val dx: Int) {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1)
}

data class Point(val y: Int, val x: Int) {
    fun move(dir: Direction) = Point(y + dir.dy, x + dir.dx)
}