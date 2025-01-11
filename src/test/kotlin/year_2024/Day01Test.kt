package year_2024

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import taskData
import year_2024.day_01.Task1
import year_2024.day_01.Task2
import kotlin.test.Test
import kotlin.test.assertEquals

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

    fun transform(input: String): Pair<List<Long>, List<Long>> {
        val left = mutableListOf<Long>()
        val right = mutableListOf<Long>()
        input.lines().forEach { line ->
            val parts = line.split("   ")
            left.add(parts[0].toLong())
            right.add(parts[1].toLong())
        }
        return left to right
    }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val (list1, list2) = transform(input)
            assertEquals(11, Task1.solve(list1, list2))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 1).readText()
            val (list1, list2) = transform(input)
            assertEquals(2378066, Task1.solve(list1, list2))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val (list1, list2) = transform(input)
            assertEquals(31, Task2.solve(list1, list2))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 1).readText()
            val (list1, list2) = transform(input)
            assertEquals(18934359, Task2.solve(list1, list2))
        }
    }
}