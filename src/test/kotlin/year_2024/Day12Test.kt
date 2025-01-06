package year_2024

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import year_2024.day_12.Task1
import year_2024.day_12.Task2
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 12: Garden Groups")
class Day12Test {

    fun transform(input: String) = input.lines(). map { it.toList() }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `should successfully pass task example 1`() {
            val map = """
                AAAA
                BBCD
                BBCC
                EEEC
            """.trimIndent()
            val input = transform(map)
            assertEquals(140, Task1.solve(input))
        }

        @Test
        fun `should successfully pass task example 2`() {
            val map = """
                OOOOO
                OXOXO
                OOOOO
                OXOXO
                OOOOO
            """.trimIndent()
            val input = transform(map)
            assertEquals(772, Task1.solve(input))
        }

        @Test
        fun `should successfully pass task example 3`() {
            val map = """
                RRRRIICCFF
                RRRRIICCCF
                VVRRRCCFFF
                VVRCCCJFFF
                VVVVCJJCFE
                VVIVCCJJEE
                VVIIICJJEE
                MIIIIIJJEE
                MIIISIJEEE
                MMMISSJEEE
            """.trimIndent()
            val input = transform(map)
            assertEquals(1930, Task1.solve(input))
        }

        @Test
        fun `Actual answer`() {
            val map = taskData(2024, 12).readText()
            val input = transform(map)
            assertEquals(1457298, Task1.solve(input))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `should successfully pass task example 1`() {
            val map = """
                AAAA
                BBCD
                BBCC
                EEEC
            """.trimIndent()
            val input = transform(map)
            assertEquals(80, Task2.solve(input))
        }

        @Test
        fun `should successfully pass task example 2`() {
            val map = """
                OOOOO
                OXOXO
                OOOOO
                OXOXO
                OOOOO
            """.trimIndent()
            val input = transform(map)
            assertEquals(436, Task2.solve(input))
        }

        @Test
        fun `should successfully pass task example 3`() {
            val map = """
                EEEEE
                EXXXX
                EEEEE
                EXXXX
                EEEEE
            """.trimIndent()
            val input = transform(map)
            assertEquals(236, Task2.solve(input))
        }

        @Test
        fun `should successfully pass task example 4`() {
            val map = """
                AAAAAA
                AAABBA
                AAABBA
                ABBAAA
                ABBAAA
                AAAAAA
            """.trimIndent()
            val input = transform(map)
            assertEquals(368, Task2.solve(input))
        }

        @Test
        fun `should successfully pass task example 5`() {
            val map = """
                RRRRIICCFF
                RRRRIICCCF
                VVRRRCCFFF
                VVRCCCJFFF
                VVVVCJJCFE
                VVIVCCJJEE
                VVIIICJJEE
                MIIIIIJJEE
                MIIISIJEEE
                MMMISSJEEE
            """.trimIndent()
            val input = transform(map)
            assertEquals(1206, Task2.solve(input))
        }

        @Test
        fun `Actual answer`() {
            val map = taskData(2024, 12).readText()
            val input = transform(map)
            assertEquals(921636, Task2.solve(input))
        }
    }
}