package day_21

data class Point(val y: Int, val x: Int) {
    operator fun plus(other: Point): Point {
        return Point(y + other.y, x + other.x)
    }
}

enum class Direction(val c: Char, val delta: Point) {
    UP('^', Point(-1, 0)),
    RIGHT('>', Point(0, 1)),
    DOWN('v', Point(1, 0)),
    LEFT('<', Point(0, -1));
}

val numericKeypad = buildMap {
    put('7', Point(0, 0)); put('8', Point(0, 1)); put('9', Point(0, 2))
    put('4', Point(1, 0)); put('5', Point(1, 1)); put('6', Point(1, 2))
    put('1', Point(2, 0)); put('2', Point(2, 1)); put('3', Point(2, 2))
                                 put('0', Point(3, 1)); put('A', Point(3, 2))
}

val directionalKeypad = buildMap {
                                 put('^', Point(0, 1)); put('A', Point(0, 2))
    put('<', Point(1, 0)); put('v', Point(1, 1)); put('>', Point(1, 2))
}






