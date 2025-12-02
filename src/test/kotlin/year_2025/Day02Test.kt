package year_2025

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 2: Gift Shop")
class Day02Test {
    companion object {
        val testInput = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528," +
                "446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"

        val realInput = TaskData(2025, 2).asString()

        @JvmStatic
        fun part1Data() = listOf(
            arguments(testInput, 1227775554),
            arguments(realInput, 15873079081)
        )

        @JvmStatic
        fun part2Data() = listOf(
            arguments(testInput, 4174379265),
            arguments(realInput, 22617871034)
        )
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: String, result: Long) {
        assertThat(
            Day02(input).solvePart1()
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: String, result: Long) {
        assertThat(
            Day02(input).solvePart2()
        ).isEqualTo(result)
    }
}