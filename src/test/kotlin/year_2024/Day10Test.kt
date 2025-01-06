package year_2024

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import year_2024.day_10.BLOCK
import year_2024.day_10.IslandMap
import year_2024.day_10.Point
import year_2024.day_10.Task1
import year_2024.day_10.Task1.Companion.getScore
import year_2024.day_10.Task2
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 10: Hoof It")
class Day10Test {

    val input = """
        89010123
        78121874
        87430965
        96549874
        45678903
        32019012
        01329801
        10456732
    """.trimIndent()

    fun transform(input: String): IslandMap {
        val lines = input.lines()
        val rows = lines.map { line ->
            line.map { if (it == '.') BLOCK else it.digitToInt() }
        }
        return IslandMap(rows)
    }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `check score function`(){
            val input =  """
            ...0...
            ...1...
            ...2...
            6543456
            7.....7
            8.....8
            9.....9
        """.trimIndent()
            val data = transform(input)
            assertEquals(2, data.getScore(Point(0, 3)))
        }

        @Test
        fun `check score function 2`(){
            val input =  """
            ..90..9
            ...1.98
            ...2..7
            6543456
            765.987
            876....
            987....
        """.trimIndent()
            val data = transform(input)
            assertEquals(4, data.getScore(Point(0, 3)))
        }

        @Test
        fun `check score function 3`(){
            val input =  """
            10..9..
            2...8..
            3...7..
            4567654
            ...8..3
            ...9..2
            .....01
        """.trimIndent()
            val data = transform(input)
            assertEquals(1, data.getScore(Point(0, 1)))
            assertEquals(2, data.getScore(Point(6, 5)))
        }

        @Test
        fun `Matches example`() {
            val data = transform(input)
            assertEquals(36, Task1.solve(data))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 10).readText()
            val data = transform(input)
            assertEquals(501, Task1.solve(data))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val data = transform(input)
            assertEquals(81, Task2.solve(data))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 10).readText()
            val data = transform(input)
            assertEquals(1017, Task2.solve(data))
        }
    }
}