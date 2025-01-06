package year_2024

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import year_2024.day_14.Robot
import year_2024.day_14.Task1
import year_2024.day_14.Task2
import year_2024.day_14.getPoint
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 14: Restroom Redoubt")
class Day14Test {

    fun transform(input: String) = input.lines()
        .map { line ->
            val (c, d) = line.split(" ")
            val coord = getPoint(c).let { Point(it.second, it.first) }
            val delta = getPoint(d).let { Point(it.second, it.first) }
            Robot(coord, delta)
        }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val config = """
                p=0,4 v=3,-3
                p=6,3 v=-1,-3
                p=10,3 v=-1,2
                p=2,0 v=2,-1
                p=0,0 v=1,3
                p=3,0 v=-2,-2
                p=7,6 v=-1,-3
                p=3,0 v=-1,-2
                p=9,3 v=2,3
                p=7,3 v=-1,2
                p=2,4 v=2,-3
                p=9,5 v=-3,-3
            """.trimIndent()
            val input = transform(config)
            assertEquals(12, Task1.solve(7, 11, input))
        }

        @Test
        fun `Actual answer`() {
            val map = taskData(2024, 14).readText()
            val input = transform(map)
            assertEquals(230461440, Task1.solve(103, 101, input))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Actual answer`() {
            val map = taskData(2024, 14).readText()
            val input = transform(map)
            assertEquals(6668, Task2.solve(103, 101, input))
        }
    }
}