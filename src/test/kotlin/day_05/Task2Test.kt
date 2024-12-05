package day_05

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task2Test {

    @Test
    fun `should successfully pass task example`() {
        val rules = listOf(
            47 to 53,
            97 to 13,
            97 to 61,
            97 to 47,
            75 to 29,
            61 to 13,
            75 to 53,
            29 to 13,
            97 to 29,
            53 to 29,
            61 to 53,
            97 to 53,
            61 to 29,
            47 to 13,
            75 to 47,
            97 to 75,
            47 to 61,
            75 to 61,
            47 to 29,
            75 to 13,
            53 to 13,
        )
        val updates = listOf(
            listOf(75, 47, 61, 53, 29),
            listOf(97, 61, 53, 29, 13),
            listOf(75, 29, 13),
            listOf(75, 97, 47, 61, 53),
            listOf(61, 13, 29),
            listOf(97, 13, 75, 29, 47),
        )
        assertEquals(123, Task2.solve(rules, updates))
    }

    @Test
    fun `should successfully solve the real task`() {
        val rules = mutableListOf<Pair<Int, Int>>()
        val updates = mutableListOf<List<Int>>()
        File("src/test/resources/day_05/TaskData.txt").readLines().forEach { line ->
            if (line.contains('|'))
                rules += readRule(line)
            else if (line.contains(','))
                updates += readUpdate(line)
        }
        println("Task solution: ${Task2.solve(rules, updates)}")    // 6191
    }

    private fun readRule(line: String): Pair<Int, Int> {
        val first = line.substringBefore('|')
        val second = line.substringAfter('|')
        return first.toInt() to second.toInt()
    }

    private fun readUpdate(line: String): List<Int> {
        return """\d+""".toRegex().findAll(line).map { it.value.toInt() }.toList()
    }
}