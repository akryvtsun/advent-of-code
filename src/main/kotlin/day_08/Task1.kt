package day_08

data class Position(val y: Int, val x: Int) {
    operator fun plus(pos: Position) = Position(y + pos.y, x + pos.x)
    operator fun minus(pos: Position) = Position(y - pos.y, x - pos.x)
}

data class Antenna(val pos: Position, val type: Char)

data class Data(val height: Int, val width: Int, val antennas: List<Antenna>)

class Task1 {

    companion object {

        fun solve(data: Data): Int {

            fun isInMap(pos: Position) =
                pos.y in 0 until data.height &&
                pos.x in 0 until data.width

            return data.antennas.groupBy { it.type }
                .map { it.value }
                .flatMap { findAntinodes(it) }
                .filter { isInMap(it) }
                .distinct()
                .count()
        }

        private fun findAntinodes(antennas: List<Antenna>): List<Position> {
            val antinodes = mutableListOf<Position>()
            for (i in 0 until antennas.size-1) {
                for (j in i+1 until antennas.size) {
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
    }
}