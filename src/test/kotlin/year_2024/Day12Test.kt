package year_2024

import TaskData
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 12: Garden Groups")
class Day12Test {
    companion object {
        val map1 = """
            AAAA
            BBCD
            BBCC
            EEEC
        """.trimIndent()

        val map2 = """
            OOOOO
            OXOXO
            OOOOO
            OXOXO
            OOOOO
        """.trimIndent()

        val map3 = """
            RRRRIICCFF
            RRRRIICCCF
            VVRRRCCFFF
            VVRCCCJFFF
            VVVVCJJCFE
            VVIVCCJJEE
            VVIIICJJEE
            MIIIIIJJEE
            MIIISIJEEE
            MMMISSJEEE
        """.trimIndent()

        val map4 = """
            EEEEE
            EXXXX
            EEEEE
            EXXXX
            EEEEE
        """.trimIndent()

        val map5 = """
            AAAAAA
            AAABBA
            AAABBA
            ABBAAA
            ABBAAA
            AAAAAA
        """.trimIndent()

        val realInput = TaskData(2024, 12).asString()

        @JvmStatic
        fun part1Data() = listOf(
            arguments(map1, 140),
            arguments(map2, 772),
            arguments(map3, 1930),
            arguments(realInput, 1457298)
        )

        @JvmStatic
        fun part2Data() = listOf(
            arguments(map1, 80),
            arguments(map2, 436),
            arguments(map4, 236),
            arguments(map5, 368),
            arguments(map3, 1206),
            arguments(realInput, 921636)
        )
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: String, result: Long) {
        Assertions.assertThat(
            Day12(input).solvePart1()
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: String, result: Long) {
        assertThat(
            Day12(input).solvePart2()
        ).isEqualTo(result)
    }
}