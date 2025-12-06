package year_2025

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 1: Secret Entrance")
class Day01Test {
    companion object {
        val testInput = """
            L68
            L30
            R48
            L5
            R60
            L55
            L1
            L99
            R14
            L82
        """.trimIndent().lines()

        val realInput = TaskData(2025, 1).asLines()

        @JvmStatic
        fun part1Data() = listOf(
            arguments(testInput, 3),
            arguments(realInput, 1081)
        )

        @JvmStatic
        fun part2Data() = listOf(
            arguments(testInput, 6),
            arguments(realInput, 6689)
        )
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: List<String>, result: Int) {
        assertThat(
            Day01(input).solvePart1()
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: List<String>, result: Int) {
        assertThat(
            Day01(input).solvePart2()
        ).isEqualTo(result)
    }
}