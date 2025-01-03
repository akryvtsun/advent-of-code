package year_2024.day_14

import year_2024.Point

data class Robot(val coord: Point, val delta: Point)

fun transform(input: String) = input.lines()
    .map { line ->
        val (c, d) = line.split(" ")
        val coord = getPoint(c).let { Point(it.second, it.first) }
        val delta = getPoint(d).let { Point(it.second, it.first) }
        Robot(coord, delta)
    }

private fun getPoint(data: String): Pair<Int, Int> {
    val (x, y) = """[pv]=(.+),(.+)""".toRegex().find(data)!!.destructured
    return x.toInt() to y.toInt()
}


