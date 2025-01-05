package year_2024

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import year_2024.day_07.Equation
import year_2024.day_07.Task1
import year_2024.day_07.Task2
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 7: Bridge Repair")
class Day07Test {

    private val input = """
        190: 10 19
        3267: 81 40 27
        83: 17 5
        156: 15 6
        7290: 6 8 6 15
        161011: 16 10 13
        192: 17 8 14
        21037: 9 7 18 13
        292: 11 6 16 20
    """.trimIndent()

    private fun transform(input: String): List<Equation> {
        return input.split("\n")
            .map { line ->
                val test = line.substringBefore(':').toLong()
                val nums = line.substringAfter(": ").split(" ").map { it.toLong() }
                Equation(test, nums)
            }
    }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val data = transform(input)
            assertEquals(3749, Task1.solve(data))
        }

        @Test
        fun `Actual answer`() {
            val input = readTaskData(2024, 7)
            val data = transform(input)
            assertEquals(12940396350192, Task1.solve(data))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val data = transform(input)
            assertEquals(11387, Task2.solve(data))
        }

        @Test
        fun `Actual answer`() {
            val input = readTaskData(2024, 7)
            val data = transform(input)
            assertEquals(106016735664498, Task2.solve(data))
        }
    }
}