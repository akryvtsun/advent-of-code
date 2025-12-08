package year_2024

import TaskData
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import year_2024.Day10.Companion.getScore

@DisplayName("Day 10: Hoof It")
class Day10Test {
    companion object {
        val testInput = """
            89010123
            78121874
            87430965
            96549874
            45678903
            32019012
            01329801
            10456732
        """.trimIndent()

        val realInput = TaskData(2024, 10).asString()

        @JvmStatic
        fun part1Data() = listOf(
            arguments(testInput, 36),
            arguments(realInput, 501)
        )

        @JvmStatic
        fun part2Data() = listOf(
            arguments(testInput, 81),
            arguments(realInput, 1017)
        )
    }

    @Test
    fun `check score function`() {
        val input = """
            ...0...
            ...1...
            ...2...
            6543456
            7.....7
            8.....8
            9.....9
        """.trimIndent()
        val data = Day10.transform(input)
        assertThat(data.getScore(Day10.Point(0, 3)))
            .isEqualTo(2)
    }

    @Test
    fun `check score function 2`() {
        val input = """
            ..90..9
            ...1.98
            ...2..7
            6543456
            765.987
            876....
            987....
        """.trimIndent()
        val data = Day10.transform(input)
        assertThat(data.getScore(Day10.Point(0, 3)))
            .isEqualTo(4)
    }

    @Test
    fun `check score function 3`() {
        val input = """
            10..9..
            2...8..
            3...7..
            4567654
            ...8..3
            ...9..2
            .....01
        """.trimIndent()
        val data = Day10.transform(input)
        assertThat(data.getScore(Day10.Point(0, 1)))
            .isEqualTo(1)
        assertThat(data.getScore(Day10.Point(6, 5)))
            .isEqualTo(2)
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: String, result: Long) {
        Assertions.assertThat(
            Day10(input).solvePart1()
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: String, result: Long) {
        assertThat(
            Day10(input).solvePart2()
        ).isEqualTo(result)
    }
}