package year_2025

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import taskData
import kotlin.test.Test

@DisplayName("Day 1")
class Day01Test {

    val input = """
        3   4
        4   3
        2   5
        1   3
        3   9
        3   3
    """.trimIndent()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day01(input).solvePart1()
            assertThat(answer).isEqualTo(11)
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2025, 1).readText()
            val answer = Day01(input).solvePart1()
            assertThat(answer).isEqualTo(2378066)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
        }

        @Test
        fun `Actual answer`() {
        }
    }
}