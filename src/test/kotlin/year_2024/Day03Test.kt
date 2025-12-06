package year_2024

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 3: Mull It Over")
class Day03Test {
    companion object {
        val realInput = TaskData(2024, 3).asString()

        @JvmStatic
        fun part1Data() = listOf(
            arguments("mul(4*, mul(6,9!, ?(12,34),", 0),
            arguments("mul ( 2 , 4 )", 0),
            arguments("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))", 161),
            arguments(realInput, 173785482)
        )

        @JvmStatic
        fun part2Data() = listOf(
            arguments("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))", 48),
            arguments(realInput, 83158140)
        )
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: String, result: Long) {
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