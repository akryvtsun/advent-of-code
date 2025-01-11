package year_2024

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import taskData
import year_2024.day_02.Task1
import year_2024.day_02.Task2
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 2: Red-Nosed Reports")
class Day02Test {

    val input = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
    """.trimIndent()

    fun transform(input: String) =
        input.lines()
            .map { it.split(" ").map(String::toInt) }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val paths = transform(input)
            assertEquals(2, Task1.solve(paths))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 2).readText()
            val paths = transform(input)
            assertEquals(510, Task1.solve(paths))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val paths = transform(input)
            assertEquals(4, Task2.solve(paths))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 2).readText()
            val paths = transform(input)
            assertEquals(553, Task2.solve(paths))
        }
    }
}