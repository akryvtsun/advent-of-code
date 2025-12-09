package year_2024

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 11: Plutonian Pebbles")
class Day11Test {
    companion object {
        const val testInput = "125 17"

        val realInput = TaskData(2024, 11).asString()

        @JvmStatic
        fun part1Data() = listOf(
            arguments(testInput, 6, 22),
            arguments(testInput, 25, 55312),
            arguments(realInput, 25, 217812)
        )

        @JvmStatic
        fun part2Data() = listOf(
            arguments(testInput, 6, 22),
            arguments(realInput, 25, 217812),
            arguments(realInput, 75, 259112729857522)
        )
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: String, blinks: Int, result: Int) {
        assertThat(
            Day11(input).solvePart1(blinks)
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: String, blinks: Int, result: Long) {
        assertThat(
            Day11(input).solvePart2(blinks)
        ).isEqualTo(result)
    }
}