package year_2024.day_14

import year_2024.Point

data class Robot(val coord: Point, val delta: Point)

fun getPoint(data: String): Pair<Int, Int> {
    val (x, y) = """[pv]=(.+),(.+)""".toRegex().find(data)!!.destructured
    return x.toInt() to y.toInt()
}


