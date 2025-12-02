package year_2024

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test

@DisplayName("Day 2: Red-Nosed Reports")
class Day02Test {

    val input = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
    """.trimIndent()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            assertThat(
                Day02(input).solvePart1()
            ).isEqualTo(2)
        }

        @Test
        fun `Actual answer`() {
            assertThat(
                Day02(
                    TaskData(2024, 2).asString()
                ).solvePart1()
            ).isEqualTo(510)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            assertThat(
                Day02(input).solvePart2()
            ).isEqualTo(4)
        }

        @Test
        fun `Actual answer`() {
            assertThat(
                Day02(
                    TaskData(2024, 2).asString()
                ).solvePart2()
            ).isEqualTo(553)
        }
    }
}