package year_2025

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 8: Playground")
class Day08Test {
    companion object {
        val testInput = """
            162,817,812
            57,618,57
            906,360,560
            592,479,940
            352,342,300
            466,668,158
            542,29,236
            431,825,988
            739,650,466
            52,470,668
            216,146,977
            819,987,18
            117,168,530
            805,96,715
            346,949,466
            970,615,88
            941,993,340
            862,61,35
            984,92,344
            425,690,689
        """.trimIndent()

        val realInput = TaskData(2025, 8).asString()

        @JvmStatic
        fun part1Data() = listOf(
            arguments(testInput, 10, 40),
            arguments(realInput, 1000, 330786)
        )

        @JvmStatic
        fun part2Data() = listOf(
            arguments(testInput, 25272),
            arguments(realInput, 3276581616)
        )
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: String, pairs: Int, result: Int) {
        assertThat(
            Day08(input).solvePart1(pairs)
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: String, result: Long) {
        assertThat(
            Day08(input).solvePart2()
        ).isEqualTo(result)
    }
}