package year_2025

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 3: Lobby")
class Day03Test {
    companion object {
        val testInput = """
            987654321111111
            811111111111119
            234234234234278
            818181911112111
        """.trimIndent()

        val realInput = TaskData(2025, 3).asString()

        @JvmStatic
        fun part1Data() = listOf(
            arguments(testInput, 357),
            arguments(realInput, 16842)
        )

        @JvmStatic
        fun part2Data() = listOf(
            arguments(testInput, 3121910778619),
            arguments(realInput, 167523425665348)
        )
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: String, result: Int) {
        assertThat(
            Day03(input).solvePart1()
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: String, result: Long) {
        assertThat(
            Day03(input).solvePart2()
        ).isEqualTo(result)
    }
}