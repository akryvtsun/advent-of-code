package year_2024

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import year_2024.day_22.Task1
import year_2024.day_22.Task2
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 22: Monkey Market")
class Day22Test {

    private fun transform(input: String): List<Long> {
        return input.lines().map(String::toLong)
    }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `should successfully gen 10 secret numbers`() {
            var num = 123L
            val secrets = List(10) {
                num = Task1.nextSecret(num)
                num
            }
            assertEquals(
                listOf<Long>(
                    15887950, 16495136, 527345, 704524, 1553684,
                    12683156, 11100544, 12249484, 7753432, 5908254),
                secrets
            )
        }

        @Test
        fun `Matches example`() {
            val input = """
                1
                10
                100
                2024
            """.trimIndent()
            val data = transform(input)
            assertEquals(37327623, Task1.solve(data))
        }

        @Test
        fun `Actual answer`() {
            val input = readTaskData(2024, 22)
            val data = transform(input)
            assertEquals(14119253575, Task1.solve(data))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val input = """
                1
                2
                3
                2024
            """.trimIndent()
            val data = transform(input)
            assertEquals(23, Task2.solve(data))
        }

        @Test
        fun `Actual answer`() {
            val input = readTaskData(2024, 22)
            val data = transform(input)
            assertEquals(1600, Task2.solve(data))
        }
    }
}