package year_2024

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 13: Claw Contraption")
class Day13Test {
    companion object {
        val testInput = """
            Button A: X+94, Y+34
            Button B: X+22, Y+67
            Prize: X=8400, Y=5400
            
            Button A: X+26, Y+66
            Button B: X+67, Y+21
            Prize: X=12748, Y=12176
            
            Button A: X+17, Y+86
            Button B: X+84, Y+37
            Prize: X=7870, Y=6450
            
            Button A: X+69, Y+23
            Button B: X+27, Y+71
            Prize: X=18641, Y=10279
        """.trimIndent()

        val realInput = TaskData(2024, 13).asString()

        @JvmStatic
        fun part1Data() = listOf(
            arguments(testInput, 480),
            arguments(realInput, 28138)
        )

        @JvmStatic
        fun part2Data() = listOf(
            arguments(testInput, 875318608908),
            arguments(realInput, 108394825772874)
        )
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: String, result: Int) {
        assertThat(
            Day13(input).solvePart1()
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: String, result: Long) {
        assertThat(
            Day13(input).solvePart2()
        ).isEqualTo(result)
    }
}