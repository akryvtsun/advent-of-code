package year_2025

import TaskData
import UNKNOWN_VALUE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 5: Cafeteria")
class Day05Test {
    companion object {
        val testInput = """
            3-5
            10-14
            16-20
            12-18

            1
            5
            8
            11
            17
            32
        """.trimIndent()

        val realInput = TaskData(2025, 5).asString()

        @JvmStatic
        fun part1Data() = listOf(
            arguments(testInput, 3),
            arguments(realInput, 640)
        )

        @JvmStatic
        fun part2Data() = listOf(
            arguments(testInput, 14),
            arguments(realInput, UNKNOWN_VALUE)
        )
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: String, result: Int) {
        assertThat(
            Day05(input).solvePart1()
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: String, result: Long) {
        assertThat(
            Day05(input).solvePart2()
        ).isEqualTo(result)
    }
}