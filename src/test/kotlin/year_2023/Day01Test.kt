package year_2023

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import taskData
import year_2023.day_01.Task1
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 1: Trebuchet?!")
class Day01Test {

    val input = """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
    """.trimIndent()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            assertEquals(142, Task1.solve(input))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2023, 1).readText()
            assertEquals(-1, Task1.solve(input))
        }
    }
}