package year_2024

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import year_2024.day_06.Direction
import year_2024.day_06.Position
import year_2024.day_06.Task1
import year_2024.day_06.Task2
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 6: Guard Gallivant")
class Day06Test {

    val input = """
        ....#.....
        .........#
        ..........
        ..#.......
        .......#..
        ..........
        .#..^.....
        ........#.
        #.........
        ......#...
    """.trimIndent()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            assertEquals(41, Task1.solve(input.split("\n").map { StringBuilder(it) }))
        }

        @Test
        fun `Actual answer`() {
            val map = taskData(2024, 6).readLines().map { StringBuilder(it) }
            assertEquals(4433, Task1.solve(map))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        private fun doTest(input: String): Int {
            var initPos: Position? = null
            val obstacles = mutableListOf<Position>()
            var width: Int? = null
            val lines = input.split('\n')
            var height = lines.size
            lines.forEachIndexed { i, line ->
                line.forEachIndexed { j, char ->
                    when (char) {
                        '#' -> obstacles.add(Position(i, j))
                        '^' -> initPos = Position(i, j)
                    }
                }
                width = line.length
            }

            return Task2.solve(height, width!!, obstacles, initPos!!, Direction.UP)
        }

        @Test
        fun `Matches example`() {
            val result = doTest(input)
            assertEquals(6, result)
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 6).readText()
            val result = doTest(input)
            assertEquals(1516, result)
        }
    }
}