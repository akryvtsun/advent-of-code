package day_18

data class Point(val y: Int, val x: Int) {
//    operator fun plus(other: Point): Point {
//        return Point(y + other.y, x + other.x)
//    }
}

fun transform(input: String): List<Point> {
    return input.lines().map { line ->
        val (x, y) = line.split(',')
        Point(y.toInt(), x.toInt())
    }
}






