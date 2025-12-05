package year_2019

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 1: The Tyranny of the Rocket Equationa")
class Day01Test {
    companion object {
        val testInput = """
            14
            1969
            100756
        """.trimIndent()

        val realInput = TaskData(2019, 1).asString()

        @JvmStatic
        fun part1Data() = listOf(
            arguments(testInput, 34239),
            arguments(realInput, 3256114)
        )

        @JvmStatic
        fun part2Data() = listOf(
            arguments(testInput, 51314),
            arguments(realInput, 4881302)
        )
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: String, result: Int) {
        assertThat(
            Day01(input).solvePart1()
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: String, result: Long) {
        assertThat(
            Day01(input).solvePart2()
        ).isEqualTo(result)
    }
}