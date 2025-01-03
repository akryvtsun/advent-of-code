package year_2024.day_18

import year_2024.Point

enum class Direction(val delta: Point) {
    UP(Point(-1, 0)),
    RIGHT(Point(0, 1)),
    DOWN(Point(1, 0)),
    LEFT(Point(0, -1));
}

fun transform(input: String): List<Point> {
    return input.lines().map { line ->
        val (x, y) = line.split(',')
        Point(y.toInt(), x.toInt())
    }
}






