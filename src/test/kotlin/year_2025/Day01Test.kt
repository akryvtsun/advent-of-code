package year_2025

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

@DisplayName("Day 1")
class Day01Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day01(0).solvePart1()
            assertThat(answer).isEqualTo(0)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day01(1).solvePart1()
            assertThat(answer).isEqualTo(1)
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