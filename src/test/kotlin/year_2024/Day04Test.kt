package year_2024

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 4: Ceres Search")
class Day04Test {
    companion object {
        val realInput = TaskData(2024, 4).asLines()

        @JvmStatic
        fun part1Data(): List<Arguments> {
            val ex1 = listOf(
                "..X...",
                ".SAMX.",
                ".A..A.",
                "XMAS.S",
                ".X....",
            )

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

            return listOf(
                arguments(ex1, 4),
                arguments(ex2, 18),
                arguments(realInput, 2575)
            )
        }

        @JvmStatic
        fun part2Data(): List<Arguments> {
            val ex1 = listOf(
                "M.S",
                ".A.",
                "M.S",
            )

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

            return listOf(
                arguments(ex1, 1),
                arguments(ex2, 9),
                arguments(realInput, 2041)
            )
        }
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: List<String>, result: Int) {
        assertThat(
            Day04(input).solvePart1()
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: List<String>, result: Int) {
        assertThat(
            Day04(input).solvePart2()
        ).isEqualTo(result)
    }
}