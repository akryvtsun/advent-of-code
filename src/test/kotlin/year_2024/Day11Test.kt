package year_2024

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import year_2024.day_11.Task1
import year_2024.day_11.Task2
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 11: Plutonian Pebbles")
class Day11Test {

    fun transform(input: String): List<Long> = input.split(" ").map { it.toLong() }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `test blink function`(){
            assertEquals(listOf<Long>(1, 2024, 1, 0, 9, 9, 2021976), Task1.blink(listOf(0, 1, 10, 99, 999)))
        }

        @Test
        fun `Matches example`() {
            val input = transform("125 17")
            assertEquals(22, Task1.solve(input, 6))
            assertEquals(55312, Task1.solve(input, 25))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 11).readText()
            val data = transform(input)
            assertEquals(217812, Task1.solve(data, 25))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `should successfully pass task example`() {
            val input = transform("125 17")
            assertEquals(22, Task2.solve(input, 6))
            assertEquals(55312, Task2.solve(input, 25))
        }

        @Test
        fun `Matches example`() {
            val input = taskData(2024, 11).readText()
            val data = transform(input)
            assertEquals(217812, Task2.solve(data, 25))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 11).readText()
            val data = transform(input)
            assertEquals(259112729857522, Task2.solve(data, 75))
        }
    }
}