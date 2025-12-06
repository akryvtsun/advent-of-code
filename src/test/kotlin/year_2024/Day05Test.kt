package year_2024

import TaskData
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 5: Print Queue")
class Day05Test {
    companion object {
        val testInput = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13
            
            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47
        """.trimIndent()

        val realInput = TaskData(2024, 5).asString()

        @JvmStatic
        fun part1Data() = listOf(
            arguments(testInput, 143),
            arguments(realInput, 5275)
        )

        @JvmStatic
        fun part2Data() = listOf(
            arguments(testInput, 123),
            arguments(realInput, 6191)
        )
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: String, result: Int) {
        Assertions.assertThat(
            Day05(input).solvePart1()
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: String, result: Int) {
        Assertions.assertThat(
            Day05(input).solvePart2()
        ).isEqualTo(result)
    }
}