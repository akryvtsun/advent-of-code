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

    data class Rotation(val dir: Direction, val value: Int)

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
                    position -= it.value
                    if (position < 0) position = (position + 100) % 100
                }
                Direction.RIGHT -> {
                    position += it.value
                    if (position > 99) position %= 100
                }
            }
            if (position == 0) count++
        }
        return count
    }

    fun solvePart2(): Int {
        val rotations = transform(input)
        var position = 50
        var count = 0
        rotations.forEach {
            when (it.dir) {
                Direction.LEFT -> {
                    position -= it.value
                    if (position < 0) position = (position + 100) % 100
                }
                Direction.RIGHT -> {
                    position += it.value
                    if (position > 99) position %= 100
                }
            }
            if (position == 0) count++
        }
        return count
    }
}

