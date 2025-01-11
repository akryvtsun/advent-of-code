package year_2024

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import taskData
import year_2024.day_20.Task1
import year_2024.day_20.Task2
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 20: Race Condition")
class Day20Test {

    val input = """
        ###############
        #...#...#.....#
        #.#.#.#.#.###.#
        #S#...#.#.#...#
        #######.#.#.###
        #######.#.#...#
        #######.#.###.#
        ###..E#...#...#
        ###.#######.###
        #...###...#...#
        #.#####.#.###.#
        #.#...#.#.#...#
        #.#.#.#.#.#.###
        #...#...#...###
        ###############
    """.trimIndent()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            assertEquals(5, Task1.solve(input, 20))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 20).readText()
            assertEquals(1358, Task1.solve(input, 100))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            assertEquals(41, Task2.solve(input, 70))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 20).readText()
            assertEquals(1005856, Task2.solve(input, 100))
        }
    }
}