package year_2023

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

@DisplayName("Day 2: Cube Conundrum")
class Day02Test {

    val input = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            assertThat(
                Day02(input).solvePart1()
            ).isEqualTo(8)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day02(
                TaskData(2023, 2).asLines()
            ).solvePart1()
            assertThat(answer).isEqualTo(1867)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            assertThat(
                Day02(input).solvePart2()
            ).isEqualTo(2286)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day02(
                TaskData(2023, 2).asLines()
            ).solvePart2()
            assertThat(answer).isEqualTo(84538)
        }
    }
}