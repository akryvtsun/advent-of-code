package year_2024

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 6: Guard Gallivant")
class Day06Test {
    companion object {
        val testInput = """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
        """.trimIndent()

        val realInput = TaskData(2024, 6).asString()

        @JvmStatic
        fun part1Data() = listOf(
            arguments(testInput, 41),
            arguments(realInput, 4433)
        )

        @JvmStatic
        fun part2Data() = listOf(
            arguments(testInput, 6),
            arguments(realInput, 1516)
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