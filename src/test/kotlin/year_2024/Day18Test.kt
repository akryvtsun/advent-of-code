package year_2024

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 18: RAM Run")
class Day18Test {
    companion object {
        val testInput = """
            5,4
            4,2
            4,5
            3,0
            2,1
            6,3
            2,4
            1,5
            0,6
            3,3
            2,6
            5,1
            1,2
            5,5
            2,5
            6,5
            1,4
            0,4
            6,4
            1,1
            6,1
            1,0
            0,5
            1,6
            2,0
        """.trimIndent()

        val realInput = TaskData(2024, 18).asString()

        @JvmStatic
        fun part1Data() = listOf(
            arguments(testInput, 12, 7, 7, 22),
            arguments(realInput, 1024, 71, 71, 304)
        )

        @JvmStatic
        fun part2Data() = listOf(
            arguments(testInput, 7, 7, Point(6,1)),
            arguments(realInput, 71, 71, Point(50,28))
        )
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: String, length: Int, height: Int, width: Int, result: Int) {
        assertThat(
            Day18(input).solvePart1(length, height, width)
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: String, height: Int, width: Int, result: Point) {
        assertThat(
            Day18(input).solvePart2(height, width)
        ).isEqualTo(result)
    }
}