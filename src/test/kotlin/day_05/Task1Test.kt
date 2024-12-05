package day_05

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Task1Test {

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
            listOf(75,47,61,53,29),
            listOf(97,61,53,29,13),
            listOf(75,29,13),
            listOf(75,97,47,61,53),
            listOf(61,13,29),
            listOf(97,13,75,29,47),
        )
        assertEquals(143, Task1.solve(rules, updates))
    }

//    @Test
//    fun `should successfully solve the real task`() {
//        File("src/test/resources/day_04/TaskData.txt")
//            .readLines()
//            .also { println("Task solution: ${Task1.solve(it)}") }  // 2575
//    }
}