package year_2024.day_16

import year_2024.Point

enum class Direction(val delta: Point) {
    UP(Point(-1, 0)),
    RIGHT(Point(0, 1)),
    DOWN(Point(1, 0)),
    LEFT(Point(0, -1));
}

data class Step(val pos: Point, val dir: Direction)

data class Labyrinth(val start: Point, val end: Point, val obstacles: Set<Point>)








