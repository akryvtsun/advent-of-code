package year_2024.day_15

data class Point(val y: Int, val x: Int) {
    operator fun plus(other: Point): Point {
        return Point(y + other.y, x + other.x)
    }
}

enum class Command(val c: Char, val delta: Point) {
    UP('^', Point(-1, 0)),
    DOWN('v', Point(1, 0)),
    LEFT('<', Point(0, -1)),
    RIGHT('>', Point(0, 1));
}





