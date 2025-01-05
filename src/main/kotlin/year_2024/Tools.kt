package year_2024

import java.io.File

fun taskData(year: Int, task: Int) =
    File("src/test/resources/year_$year/day_${String.format("%02d", task)}/TaskData.txt")

data class Point(val y: Int, val x: Int) {
    operator fun plus(other: Point): Point {
        return Point(y + other.y, x + other.x)
    }
}