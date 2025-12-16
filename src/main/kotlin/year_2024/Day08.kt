package year_2024

import AocDay

class Day08(input: String) : AocDay<Int, Int>(input) {

    data class Position(val y: Int, val x: Int) {
        operator fun plus(pos: Position) = Position(y + pos.y, x + pos.x)
        operator fun minus(pos: Position) = Position(y - pos.y, x - pos.x)
    }

    data class Antenna(val pos: Position, val type: Char)

    data class Data(val height: Int, val width: Int, val antennas: List<Antenna>)

    fun transform(input: String): Data {
        val lines = input.split("\n")
        val height = lines.size
        val width = lines.first().length
        val antennas = lines.flatMapIndexed { i, line ->
            val lineList = mutableListOf<Antenna>()
            for (j in line.indices) {
                if (line[j] != '.') {
                    lineList += Antenna(Position(i, j), line[j])
                }
            }
            lineList
        }
        return Data(height, width, antennas)
    }

    val data = transform(input)

    fun isInMap(pos: Position) =
        pos.y in 0 until data.height &&
                pos.x in 0 until data.width

    override fun solvePart1(): Int {
        return data.antennas.groupBy { it.type }
            .map { it.value }
            .flatMap { findAntinodes(it) }
            .filter { isInMap(it) }
            .distinct()
            .count()
    }

    private fun findAntinodes(antennas: List<Antenna>): List<Position> {
        val antinodes = mutableListOf<Position>()
        for (i in 0 until antennas.size - 1) {
            for (j in i + 1 until antennas.size) {
                val a = antennas[i]
                val b = antennas[j]
                antinodes.add(point1(a.pos, b.pos))
                antinodes.add(point2(a.pos, b.pos))
            }
        }
        return antinodes
    }

    private fun point1(a: Position, b: Position): Position {
        val delta = a - b
        return a + delta
    }

    private fun point2(a: Position, b: Position): Position {
        val delta = a - b
        return b - delta
    }

    override fun solvePart2(): Int {

        fun genPoints1(a: Position, b: Position): List<Position> {
            val positions = mutableListOf<Position>()
            val delta = a - b
            var curPos = a
            do {
                positions.add(curPos)
                curPos += delta
            } while (isInMap(curPos))
            return positions
        }

        fun genPoints2(a: Position, b: Position): List<Position> {
            val positions = mutableListOf<Position>()
            val delta = a - b
            var curPos = b
            do {
                positions.add(curPos)
                curPos -= delta
            } while (isInMap(curPos))
            return positions
        }

        fun findAntinodes(antennas: List<Antenna>): List<Position> {
            val antinodes = mutableListOf<Position>()
            for (i in 0 until antennas.size - 1) {
                for (j in i + 1 until antennas.size) {
                    val a = antennas[i]
                    val b = antennas[j]
                    antinodes.addAll(genPoints1(a.pos, b.pos))
                    antinodes.addAll(genPoints2(a.pos, b.pos))
                }
            }
            return antinodes
        }

        return data.antennas.groupBy { it.type }
            .map { it.value }
            .flatMap { findAntinodes(it) }
            .distinct()
            .count()
    }
}