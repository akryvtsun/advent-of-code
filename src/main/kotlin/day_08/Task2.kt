package day_08

class Task2 {

    companion object {

        fun solve(data: Data): Int {

            fun isInMap(pos: Position) =
                pos.y in 0 until data.height &&
                pos.x in 0 until data.width

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
                for (i in 0 until antennas.size-1) {
                    for (j in i+1 until antennas.size) {
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
}