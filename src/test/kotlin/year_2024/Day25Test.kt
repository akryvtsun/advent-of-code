package year_2024

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import year_2024.day_25.Task1
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 25: Code Chronicle")
class Day25Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val input = """
                #####
                .####
                .####
                .####
                .#.#.
                .#...
                .....
                
                #####
                ##.##
                .#.##
                ...##
                ...#.
                ...#.
                .....
                
                .....
                #....
                #....
                #...#
                #.#.#
                #.###
                #####
                
                .....
                .....
                #.#..
                ###..
                ###.#
                ###.#
                #####
                
                .....
                .....
                .....
                #....
                #.#..
                #.#.#
                #####
            """.trimIndent()
            val (locks, keys) = Task1.transform(input)
            assertEquals(3, Task1.solve(locks, keys))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 25).readText()
            val (locks, keys) = Task1.transform(input)
            assertEquals(3077, Task1.solve(locks, keys))
        }
    }
}