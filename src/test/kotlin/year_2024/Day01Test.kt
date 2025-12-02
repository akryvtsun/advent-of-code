package year_2024

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

@DisplayName("Day 1: Historian Hysteria")
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
            assertThat(
                Day01(input).solvePart1()
            ).isEqualTo(11)
        }

        @Test
        fun `Actual answer`() {
            assertThat(
                Day01(
                    TaskData(2024, 1).asString()
                ).solvePart1()
            ).isEqualTo(2378066)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            assertThat(
                Day01(input).solvePart2()
            ).isEqualTo(31)
        }

        @Test
        fun `Actual answer`() {
            assertThat(
                Day01(
                    TaskData(2024, 1).asString()
                ).solvePart2()
            ).isEqualTo(18934359)
        }
    }
}