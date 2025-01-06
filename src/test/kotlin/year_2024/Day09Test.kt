package year_2024

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import year_2024.day_09.Task1
import year_2024.day_09.Task2
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 9: Disk Fragmenter")
class Day09Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            assertEquals(1928, Task1.solve("2333133121414131402"))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 9).readText()
            assertEquals(6386640365805, Task1.solve(input))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            assertEquals(2858, Task2.solve("2333133121414131402"))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 9).readText()
            assertEquals(6423258376982, Task2.solve(input))
        }
    }
}