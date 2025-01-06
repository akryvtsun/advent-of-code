package year_2024.day_18

import year_2024.Point

enum class Direction(val delta: Point) {
    UP(Point(-1, 0)),
    RIGHT(Point(0, 1)),
    DOWN(Point(1, 0)),
    LEFT(Point(0, -1));
}








