package day_16

data class Point(val y: Int, val x: Int) {
    operator fun plus(other: Point): Point {
        return Point(y + other.y, x + other.x)
    }
}

enum class Direction(val delta: Point) {
    UP(Point(-1, 0)),
    RIGHT(Point(0, 1)),
    DOWN(Point(1, 0)),
    LEFT(Point(0, -1));
}

data class Step(val pos: Point, val dir: Direction)

data class Labyrinth(val start: Point, val end: Point, val obstacles: Set<Point>)

fun transform(input: String): Labyrinth {
    var start: Point? = null
    var end: Point? = null
    val obstacles = mutableSetOf<Point>()
    val lines = input.lines()
    for (y in lines.indices) {
        for (x in lines.first().indices) {
            when (lines[y][x]) {
                '#' -> obstacles.add(Point(y, x))
                'S' -> start = Point(y, x)
                'E' -> end = Point(y, x)
            }
        }
    }
    return Labyrinth(start!!, end!!, obstacles)
}






