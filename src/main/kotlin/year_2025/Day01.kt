package year_2025

import AocDay

class Day01(input: String) : AocDay<Int, Int>(input) {

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

    private fun transform(input: String): List<Rotation> =
        input.lines()
            .map { Direction.of(it[0]) to it.substring(1).toInt() }
            .map { Rotation(it.first, it.second) }

    override fun solvePart1(): Int {
        val rotations = transform(input)
        var position = 50
        var count = 0
        // it's possible to use here solution from part 2
        // but only move count calculation outside of repeat cycle
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
    override fun solvePart2(): Int {
        var position = 50
        var count = 0
        // no input transformation not data classes - the simplest solution
        input.lines().forEach { r ->
            val times = r.substring(1).toInt()
            repeat(times) {
                position += if (r[0] == 'L') -1 else 1
                if (position == -1) position = 99
                if (position == 100) position = 0
                if (position == 0) count++
            }
        }
        return count
    }
}

