package year_2023

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

@DisplayName("Day 1: Trebuchet?!")
class Day01Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        val input = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
        """.trimIndent()

        @Test
        fun `Matches example`() {
            assertThat(
                Day01(input).solvePart1()
            ).isEqualTo(142)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day01(
                TaskData(2023, 1).asString()
            ).solvePart1()
            assertThat(answer).isEqualTo(54338)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        val input = """
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
        """.trimIndent()

        @Test
        fun `Matches example`() {
            assertThat(
                Day01(input).solvePart2()
            ).isEqualTo(281)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day01(
                TaskData(2023, 1).asString()
            ).solvePart2()
            assertThat(answer).isEqualTo(53389)
        }
    }
}