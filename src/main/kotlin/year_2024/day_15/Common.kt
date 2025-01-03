package year_2024.day_15

import year_2024.Point

enum class Command(val c: Char, val delta: Point) {
    UP('^', Point(-1, 0)),
    DOWN('v', Point(1, 0)),
    LEFT('<', Point(0, -1)),
    RIGHT('>', Point(0, 1));
}





