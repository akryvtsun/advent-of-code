package year_2023

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import taskData
import year_2023.day_01.Task1
import year_2023.day_01.Task2
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 1: Trebuchet?!")
class Day01Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        val input = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
        """.trimIndent()

        @Test
        fun `Matches example`() {
            assertEquals(142, Task1.solve(input))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2023, 1).readText()
            assertEquals(54338, Task1.solve(input))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        val input = """
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
        """.trimIndent()

        @Test
        fun `Matches example`() {
            assertEquals(281, Task2.solve(input))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2023, 1).readText()
            assertEquals(53389, Task2.solve(input))
        }
    }
}