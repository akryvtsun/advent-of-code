package year_2024

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import year_2024.day_04.Task1
import year_2024.day_04.Task2
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 4: Ceres Search")
class Day04Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val ex1 = listOf(
                "..X...",
                ".SAMX.",
                ".A..A.",
                "XMAS.S",
                ".X....",
            )
            assertEquals(4, Task1.solve(ex1))

            val ex2 = listOf(
                "MMMSXXMASM",
                "MSAMXMSMSA",
                "AMXSXMAAMM",
                "MSAMASMSMX",
                "XMASAMXAMM",
                "XXAMMXXAMA",
                "SMSMSASXSS",
                "SAXAMASAAA",
                "MAMMMXMMMM",
                "MXMXAXMASX",
            )
            assertEquals(18, Task1.solve(ex2))
        }

        @Test
        fun `Actual answer`() {
            val input = File("src/test/resources/year_2024/day_04/TaskData.txt").readText()
            assertEquals(2575, Task1.solve(input.lines()))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val ex1 = listOf(
                "M.S",
                ".A.",
                "M.S",
            )
            assertEquals(1, Task2.solve(ex1))

            val ex2 = listOf(
                ".M.S......",
                "..A..MSMS.",
                ".M.S.MAA..",
                "..A.ASMSM.",
                ".M.S.M....",
                "..........",
                "S.S.S.S.S.",
                ".A.A.A.A..",
                "M.M.M.M.M.",
                "..........",
            )
            assertEquals(9, Task2.solve(ex2))
        }

        @Test
        fun `Actual answer`() {
            val input = File("src/test/resources/year_2024/day_04/TaskData.txt").readText()
            assertEquals(2041, Task2.solve(input.lines()))
        }
    }
}