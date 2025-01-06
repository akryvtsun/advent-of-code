package year_2024

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import year_2024.day_18.Task1
import year_2024.day_18.Task2
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 18: RAM Run")
class Day18Test {

    val input = """
        5,4
        4,2
        4,5
        3,0
        2,1
        6,3
        2,4
        1,5
        0,6
        3,3
        2,6
        5,1
        1,2
        5,5
        2,5
        6,5
        1,4
        0,4
        6,4
        1,1
        6,1
        1,0
        0,5
        1,6
        2,0
    """.trimIndent()

    fun transform(input: String): List<Point> {
        return input.lines().map { line ->
            val (x, y) = line.split(',')
            Point(y.toInt(), x.toInt())
        }
    }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val bytes = transform(input)
            assertEquals(22, Task1.solve(bytes, 12, 7, 7))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 18).readText()
            val bytes = transform(input)
            assertEquals(304, Task1.solve(bytes, 1024, 71, 71))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val bytes = transform(input)
            assertEquals(Point(6,1), Task2.solve(bytes, 7, 7))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 18).readText()
            val bytes = transform(input)
            assertEquals(Point(50,28), Task2.solve(bytes, 71, 71))
        }
    }
}