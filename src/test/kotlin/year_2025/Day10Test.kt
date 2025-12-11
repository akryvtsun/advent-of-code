package year_2025

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@Disabled("part 2 is needed to solve and part 1 memory optimization")
@DisplayName("Day 10: Factory")
class Day10Test {
    companion object {
        val testInput = """
            [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
            [...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
            [.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}
        """.trimIndent()

        // 0. A*(0,2) + B*(0,1) = 3
        // 1. C*(1,3) + B*(0,1) = 5
        // 2. D*(2) + E*(2,3) + A*(0,2) = 4
        // 3. F*(3) + C*(1,3) + E*(2,3) = 7
        // A+B+C+D+E+F -> min

        // A = 1
        // B = 2
        // C = 3
        // D =
        // E = 3
        // F = 1

        // A + B = 3
        // C + B = 5
        // D + E + A = 4
        // F + C + E = 7

        // A = 3
        // B = 0
        // C = 5
        // D + E = 1 -> E = 1 - D
        // F + E = 2 -> F + 1 - D = 2 -> F - D = 1 -> F = 1 + D

        // ===========

        // 0. A(0,2,3,4)          + C(0,4) + D(0,1,2)              = 7
        // 1.                                D(0,1,2) + E(1,2,3,4) = 5
        // 2. A(0,2,3,4) + B(2,3)          + D(0,1,2) + E(1,2,3,4) = 12
        // 3. A(0,2,3,4) + B(2,3)                     + E(1,2,3,4) = 7
        // 4. A(0,2,3,4)          + C(0,4)            + E(1,2,3,4) = 2

        // A + C + D = 7        v
        // D + E = 5            V
        // A + B + D + E = 12   v
        // A + B + E = 7        V
        // A + C + E = 2
        // A+B+C+D+E -> ?

        val realInput = TaskData(2025, 10).asString()

        @JvmStatic
        fun part1Data() = listOf(
            arguments(testInput, 7),
            arguments(realInput, 505)
        )

        @JvmStatic
        fun part2Data() = listOf(
            arguments(testInput, 33),
            //arguments(realInput, UNKNOWN_VALUE)
        )
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: String, result: Int) {
        assertThat(
            Day10(input).solvePart1()
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: String, result: Int) {
        assertThat(
            Day10(input).solvePart2()
        ).isEqualTo(result)
    }
}