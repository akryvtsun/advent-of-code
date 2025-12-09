package year_2024

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 20: Race Condition")
class Day20Test {
    companion object {
        val testInput = """
            ###############
            #...#...#.....#
            #.#.#.#.#.###.#
            #S#...#.#.#...#
            #######.#.#.###
            #######.#.#...#
            #######.#.###.#
            ###..E#...#...#
            ###.#######.###
            #...###...#...#
            #.#####.#.###.#
            #.#...#.#.#...#
            #.#.#.#.#.#.###
            #...#...#...###
            ###############
        """.trimIndent()

        val realInput = TaskData(2024, 20).asString()

        @JvmStatic
        fun part1Data() = listOf(
            arguments(testInput, 20, 5),
            arguments(realInput, 100, 1358)
        )

        @JvmStatic
        fun part2Data() = listOf(
            arguments(testInput, 70, 41),
            arguments(realInput, 100, 1005856)
        )
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: String, threshold: Int, result: Int) {
        assertThat(
            Day20(input).solvePart1(threshold)
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: String, threshold: Int, result: Int) {
        assertThat(
            Day20(input).solvePart2(threshold)
        ).isEqualTo(result)
    }
}