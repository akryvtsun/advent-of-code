package year_2024

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 7: Bridge Repair")
class Day07Test {
    companion object {
        val testInput = """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
        """.trimIndent()

        val realInput = TaskData(2024, 7).asString()

        @JvmStatic
        fun part1Data() = listOf(
            arguments(testInput, 3749),
            arguments(realInput, 12940396350192)
        )

        @JvmStatic
        fun part2Data() = listOf(
            arguments(testInput, 11387),
            arguments(realInput, 106016735664498)
        )
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: String, result: Long) {
        assertThat(
            Day07(input).solvePart1()
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: String, result: Long) {
        assertThat(
            Day07(input).solvePart2()
        ).isEqualTo(result)
    }
}