package year_2024.day_15

import year_2024.Point
import year_2024.day_15.Command.*

class Task2 {

    data class Box(val left: Point, val right: Point) {
        operator fun contains(point: Point): Boolean {
            return point.y == left.y && (point.x in left.x..right.x)
        }

        operator fun plus(point: Point): Box {
            return Box(left + point, right + point)
        }
    }

    class Warehouse(map: String) {

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

    companion object {

        fun solve(data: Warehouse, commands: List<Command>): Int {
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
                                UP, DOWN -> listOf(it.left, it.right)
                                LEFT -> listOf(it.left)
                                RIGHT -> listOf(it.right)
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
}