package day_15

data class Point(val y: Int, val x: Int) {
    operator fun plus(other: Point): Point {
        return Point(y + other.y, x + other.x)
    }
}

class Warehouse(map: String) {

    val obstacles = mutableListOf<Point>()
    val boxes = mutableListOf<Point>()
    var robot: Point? = null

    init {
        val data = map.lines()
        for (y in data.indices) {
            for (x in data.first().indices) {
                val point = Point(y, x)
                when (data[y][x]) {
                    '#' -> obstacles += point
                    'O' -> boxes += point
                    '@' -> robot = point
                }
            }
        }
    }
}

enum class Command(val c: Char, val delta: Point) {
    UP('^', Point(-1, 0)),
    DOWN('v', Point(1, 0)),
    LEFT('<', Point(0, -1)),
    RIGHT('>', Point(0, 1));
}

fun transform(input: String): Pair<Warehouse, List<Command>> {
    val blocks = input.split("\n\n")
    val wrhs = Warehouse(blocks[0])
    val cmds = blocks[1]
        .filter { it != '\n' }
        .map { c -> Command.entries.first { it.c == c } }
    return wrhs to cmds
}



