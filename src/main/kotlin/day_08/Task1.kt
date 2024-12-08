package day_08

data class Position(val y: Int, val x: Int)

data class Antenna(val pos: Position, val type: Char)

data class Data(val height: Int, val width: Int, val antennas: List<Antenna>)

class Task1 {

    companion object {

        fun solve(data: Data): Int {
            return data.antennas.groupBy { it.type }
                .map { it.value }
                .flatMap { findAntinodes(it) }
                .filter { isInMap(data.height, data.width, it) }
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
            return Position(a.y + (a.y-b.y), a.x + (a.x-b.x))
        }

        private fun point2(a: Position, b: Position): Position {
            return Position(b.y - (a.y-b.y), b.x - (a.x-b.x))
        }

        private fun isInMap(height: Int, width: Int,pos: Position) =
            pos.y in 0 until height &&
            pos.x in 0 until width
    }
}