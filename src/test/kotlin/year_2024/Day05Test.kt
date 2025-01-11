package year_2024

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import taskData
import year_2024.day_05.Task1
import year_2024.day_05.Task2
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 5: Print Queue")
class Day05Test {

    val input = """
        47|53
        97|13
        97|61
        97|47
        75|29
        61|13
        75|53
        29|13
        97|29
        53|29
        61|53
        97|53
        61|29
        47|13
        75|47
        97|75
        47|61
        75|61
        47|29
        75|13
        53|13
        
        75,47,61,53,29
        97,61,53,29,13
        75,29,13
        75,97,47,61,53
        61,13,29
        97,13,75,29,47
    """.trimIndent()

    fun transform(input: String): Pair<List<Pair<Int, Int>>, List<List<Int>>> {
        val rules = mutableListOf<Pair<Int, Int>>()
        val updates = mutableListOf<List<Int>>()
        input.split("\n").forEach { line ->
            if (line.contains('|'))
                rules += readRule(line)
            else if (line.contains(','))
                updates += readUpdate(line)
        }
        return rules to updates
    }

    private fun readRule(line: String): Pair<Int, Int> {
        val first = line.substringBefore('|')
        val second = line.substringAfter('|')
        return first.toInt() to second.toInt()
    }

    private fun readUpdate(line: String): List<Int> {
        return """\d+""".toRegex().findAll(line).map { it.value.toInt() }.toList()
    }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val (rules, updates) = transform(input)
            assertEquals(143, Task1.solve(rules, updates))
        }

        @Test
        fun `Actual answer`() {
            val data = taskData(2024, 5).readText()
            val (rules, updates) = transform(data)
            assertEquals(5275, Task1.solve(rules, updates))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val (rules, updates) = transform(input)
            assertEquals(123, Task2.solve(rules, updates))
        }

        @Test
        fun `Actual answer`() {
            val data = taskData(2024, 5).readText()
            val (rules, updates) = transform(data)
            assertEquals(6191, Task2.solve(rules, updates))
        }
    }
}