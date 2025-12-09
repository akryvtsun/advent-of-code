package year_2025

import TaskData
import UNKNOWN_VALUE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 9: Movie Theater")
class Day09Test {
    companion object {
        val testInput = """
            7,1
            11,1
            11,7
            9,7
            9,5
            2,5
            2,3
            7,3
        """.trimIndent()

        val realInput = TaskData(2025, 9).asString()

        @JvmStatic
        fun part1Data() = listOf(
            arguments(testInput, 50),
            arguments(realInput, 4741451444L)
        )

        @JvmStatic
        fun part2Data() = listOf(
            arguments(testInput, UNKNOWN_VALUE),
            arguments(realInput, UNKNOWN_VALUE)
        )
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: String, result: Long) {
        assertThat(
            Day09(input).solvePart1()
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: String, result: Int) {
        assertThat(
            Day09(input).solvePart2()
        ).isEqualTo(result)
    }
}