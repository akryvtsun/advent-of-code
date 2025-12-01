package year_2025

import TaskData
import UNKNOWN_VALUE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("")
class Day02Test {
    companion object {
        val testInput = """
        """.trimIndent().lines()

        val realInput = TaskData(2025, 2).asLines()

        @JvmStatic
        fun part1Data() = listOf(
            arguments(testInput, UNKNOWN_VALUE),
            arguments(realInput, UNKNOWN_VALUE)
        )

        @JvmStatic
        fun part2Data() = listOf(
            arguments(testInput, UNKNOWN_VALUE),
            arguments(realInput, UNKNOWN_VALUE)
        )
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: List<String>, result: Int) {
        assertThat(
            Day02(input).solvePart1()
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: List<String>, result: Int) {
        assertThat(
            Day02(input).solvePart2()
        ).isEqualTo(result)
    }
}