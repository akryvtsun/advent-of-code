package year_2025

class Day01(private val input: List<String>) {

    enum class Direction {
        LEFT, RIGHT;

        companion object {
            fun of(c: Char) = when (c) {
                'L' -> LEFT
                'R' -> RIGHT
                else -> throw IllegalArgumentException("Invalid direction $c")
            }
        }
    }

    data class Rotation(val dir: Direction, val count: Int)

    private fun transform(input: List<String>): List<Rotation> =
        input
            .map { Direction.of(it[0]) to it.substring(1).toInt() }
            .map { Rotation(it.first, it.second) }

    fun solvePart1(): Int {
        val rotations = transform(input)
        var position = 50
        var count = 0
        rotations.forEach {
            when (it.dir) {
                Direction.LEFT -> {
                    position -= it.count
                    if (position < 0) position = (position + 100) % 100
                }

                Direction.RIGHT -> {
                    position += it.count
                    if (position > 99) position %= 100
                }
            }
            if (position == 0) count++
        }
        return count
    }

    // password method 0x434C49434B
    fun solvePart2(): Int {
        val rotations = transform(input)
        var position = 50
        var count = 0
        rotations.forEach { r ->
            repeat(r.count) {
                position += if (r.dir == Direction.LEFT) -1 else 1
                if (position == -1) position = 99
                if (position == 100) position = 0
                if (position == 0) count++
            }
        }
        return count
    }
}

