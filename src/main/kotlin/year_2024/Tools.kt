package year_2024

import java.io.File

fun readTaskData(year: Int, task: Int) =
    File("src/test/resources/year_$year/day_$task/TaskData.txt").readText()

data class Point(val y: Int, val x: Int) {
    operator fun plus(other: Point): Point {
        return Point(y + other.y, x + other.x)
    }
}