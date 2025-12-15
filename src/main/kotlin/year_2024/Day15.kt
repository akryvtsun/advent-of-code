package year_2024

class Day15(val input: String) {

    enum class Command(val c: Char, val delta: Point) {
        UP('^', Point(-1, 0)),
        DOWN('v', Point(1, 0)),
        LEFT('<', Point(0, -1)),
        RIGHT('>', Point(0, 1));
    }

    class Warehouse1(map: String) {
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

    fun transform1(input: String): Pair<Warehouse1, List<Command>> {
        val blocks = input.split("\n\n")
        val wrhs = Warehouse1(blocks[0])
        val cmds = blocks[1]
            .filter { it != '\n' }
            .map { c -> Command.entries.first { it.c == c } }
        return wrhs to cmds
    }

    fun solvePart1(): Int {
        val (data, commands) = transform1(input)

        val obstacles = data.obstacles.toList()
        val boxes = data.boxes
        var cur = data.robot!!
        for (cmd in commands) {
            val next = cur + cmd.delta
            if (next in obstacles) {
                continue    // skip move
            }
            if (next in boxes) {
                var nextBox = next
                do {
                    nextBox += cmd.delta
                } while (nextBox in boxes)
                if (nextBox in obstacles) {
                    continue // boxes close to obstacle in cmd direction - skip move
                } else {  // move first box in chain to finishing space
                    boxes -= next
                    boxes += nextBox
                }
            }
            cur = next // move robot to new position in cmd direction
        }
        return boxes.sumOf { it.y * 100 + it.x }
    }

    data class Box(val left: Point, val right: Point) {
        operator fun contains(point: Point): Boolean {
            return point.y == left.y && (point.x in left.x..right.x)
        }

        operator fun plus(point: Point): Box {
            return Box(left + point, right + point)
        }
    }

    class Warehouse2(map: String) {
        val obstacles = mutableListOf<Point>()
        val boxes = mutableListOf<Box>()
        var robot: Point? = null

        init {
            val data = map.lines()
            for (y in data.indices) {
                for (x in data.first().indices) {
                    val left = Point(y, x * 2)
                    val right = Point(y, x * 2 + 1)
                    when (data[y][x]) {
                        '#' -> obstacles += listOf(left, right)
                        'O' -> boxes += Box(left, right)
                        '@' -> robot = left
                    }
                }
            }
        }
    }

    fun transform2(input: String): Pair<Warehouse2, List<Command>> {
        val blocks = input.split("\n\n")
        val wrhs = Warehouse2(blocks[0])
        val cmds = blocks[1]
            .filter { it != '\n' }
            .map { c -> Command.entries.first { it.c == c } }
        return wrhs to cmds
    }

    fun solvePart2(): Int {
        val (data, commands) = transform2(input)

        val obstacles = data.obstacles.toList()
        val boxes = data.boxes
        var robotPos = data.robot!!

        outer@ for (cmd in commands) {
            val boxesForMove = mutableSetOf<Box>()
            var edge = setOf(robotPos)
            while (true) {
                var nextEdge = edge.map { it + cmd.delta }.toSet()
                if (nextEdge.any { it in obstacles }) {
                    continue@outer   // edge faced to obstacle -> skip move
                }
                val nextBoxes = nextEdge.map { edge -> boxes.firstOrNull { edge in it } }.filterNotNull().toSet()
                if (nextBoxes.isNotEmpty()) {
                    boxesForMove += nextBoxes
                    nextEdge = nextBoxes.flatMap {
                        when (cmd) {
                            Command.UP, Command.DOWN -> listOf(it.left, it.right)
                            Command.LEFT -> listOf(it.left)
                            Command.RIGHT -> listOf(it.right)
                        }
                    }.toSet()  // recalc edge line
                } else
                    break
                edge = nextEdge
            }
            if (boxesForMove.isNotEmpty()) {
                boxes -= boxesForMove
                boxes += boxesForMove.map { it + cmd.delta }
            }
            robotPos += cmd.delta
        }

        return boxes.sumOf { it.left.y * 100 + it.left.x }
    }
}