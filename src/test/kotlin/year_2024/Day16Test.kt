package year_2024

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import year_2024.day_16.Labyrinth
import year_2024.day_16.Task1
import year_2024.day_16.Task2
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 16: Reindeer Maze")
class Day16Test {

    private val input1 = """
        ###############
        #.......#....E#
        #.#.###.#.###.#
        #.....#.#...#.#
        #.###.#####.#.#
        #.#.#.......#.#
        #.#.#####.###.#
        #...........#.#
        ###.#.#####.#.#
        #...#.....#.#.#
        #.#.#.###.#.#.#
        #.....#...#.#.#
        #.###.#.#.#.#.#
        #S..#.....#...#
        ###############
    """.trimIndent()

    private val input2 = """
        #################
        #...#...#...#..E#
        #.#.#.#.#.#.#.#.#
        #.#.#.#...#...#.#
        #.#.#.#.###.#.#.#
        #...#.#.#.....#.#
        #.#.#.#.#.#####.#
        #.#...#.#.#.....#
        #.#.#####.#.###.#
        #.#.#.......#...#
        #.#.###.#####.###
        #.#.#...#.....#.#
        #.#.#.#####.###.#
        #.#.#.........#.#
        #.#.#.#########.#
        #S#.............#
        #################
    """.trimIndent()

    private fun transform(input: String): Labyrinth {
        var start: Point? = null
        var end: Point? = null
        val obstacles = mutableSetOf<Point>()
        val lines = input.lines()
        for (y in lines.indices) {
            for (x in lines.first().indices) {
                when (lines[y][x]) {
                    '#' -> obstacles.add(Point(y, x))
                    'S' -> start = Point(y, x)
                    'E' -> end = Point(y, x)
                }
            }
        }
        return Labyrinth(start!!, end!!, obstacles)
    }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example 1`() {
            val data = transform(input1)
            assertEquals(7036, Task1.solve(data))
        }

        @Test
        fun `Matches example 2`() {
            val data = transform(input2)
            assertEquals(11048, Task1.solve(data))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 16).readText()
            val data = transform(input)
            assertEquals(147628, Task1.solve(data))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example 1`() {
            val data = transform(input1)
            assertEquals(45, Task2.solve(data))
        }

        @Test
        fun `Matches example 2`() {
            val data = transform(input2)
            assertEquals(64, Task2.solve(data))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 16).readText()
            val data = transform(input)
            assertEquals(670, Task2.solve(data))
        }
    }
}