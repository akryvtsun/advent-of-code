package year_2024

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 14: Restroom Redoubt")
class Day14Test {
    companion object {
        val testInput = """
            p=0,4 v=3,-3
            p=6,3 v=-1,-3
            p=10,3 v=-1,2
            p=2,0 v=2,-1
            p=0,0 v=1,3
            p=3,0 v=-2,-2
            p=7,6 v=-1,-3
            p=3,0 v=-1,-2
            p=9,3 v=2,3
            p=7,3 v=-1,2
            p=2,4 v=2,-3
            p=9,5 v=-3,-3
        """.trimIndent()

        val realInput = TaskData(2024, 14).asString()

        @JvmStatic
        fun part1Data() = listOf(
            arguments(testInput, 7, 11, 12),
            arguments(realInput, 103, 101, 230461440)
        )

        @JvmStatic
        fun part2Data() = listOf(
            arguments(realInput, 103, 101, 6668)
        )
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: String, height: Int, width: Int, result: Int) {
        assertThat(
            Day14(input, height, width).solvePart1()
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: String, height: Int, width: Int, result: Long) {
        assertThat(
            Day14(input, height, width).solvePart2()
        ).isEqualTo(result)
    }
}