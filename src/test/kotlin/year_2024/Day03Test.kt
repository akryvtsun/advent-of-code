package year_2024

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import taskData
import year_2024.day_03.Task1
import year_2024.day_03.Task2
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 3: Mull It Over")
class Day03Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            assertEquals(0, Task1.solve("mul(4*, mul(6,9!, ?(12,34),"))
            assertEquals(0, Task1.solve("mul ( 2 , 4 )"))
            assertEquals(161, Task1.solve("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 3).readText()
            assertEquals(173785482, Task1.solve(input))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            assertEquals(48, Task2.solve("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 3).readText()
            assertEquals(83158140, Task2.solve(input))
        }
    }
}