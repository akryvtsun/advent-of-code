package year_2025

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

@DisplayName("Day 1: Secret Entrance")
class Day01Test {

    val input = """
        L68
        L30
        R48
        L5
        R60
        L55
        L1
        L99
        R14
        L82
    """.trimIndent()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            assertThat(
                Day01(input.lines()).solvePart1()
            ).isEqualTo(3)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day01(
                TaskData(2025, 1).asLines()
            ).solvePart1()
            assertThat(answer).isEqualTo(1081)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            assertThat(
                Day01(input.lines()).solvePart2()
            ).isEqualTo(6)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day01(
                TaskData(2025, 1).asLines()
            ).solvePart2()
            assertThat(answer).isEqualTo(6689)
        }
    }
}