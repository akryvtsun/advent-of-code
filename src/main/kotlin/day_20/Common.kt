package day_20

data class Point(val y: Int, val x: Int) {
    operator fun plus(other: Point): Point {
        return Point(y + other.y, x + other.x)
    }
}

enum class Direction(val delta: Point) {
    UP(Point(-1,0)),
    RIGHT(Point(0,1)),
    DOWN(Point(1,0)),
    LEFT(Point(0,-1)),
}






