package year_2025

import TaskData
import UNKNOWN_VALUE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 6: Trash Compactor")
class Day06Test {
    companion object {
        val testInput = """
            123 328  51 64 
             45 64  387 23 
              6 98  215 314
            *   +   *   +  
        """.trimIndent()

        val realInput = TaskData(2025, 6).asString()

        @JvmStatic
        fun part1Data() = listOf(
            arguments(testInput, 4277556L),
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
    fun part1Test(input: String, result: Long) {
        assertThat(
            Day06(input).solvePart1()
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: String, result: Long) {
        assertThat(
            Day06(input).solvePart2()
        ).isEqualTo(result)
    }
}