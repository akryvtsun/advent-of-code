package year_2024.day_02

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task2Test {

    @Test
    fun `should successfully pass task example`() {
        val paths = listOf(
            listOf(7, 6, 4, 2, 1),
            listOf(1, 2, 7, 8, 9),
            listOf(9, 7, 6, 2, 1),
            listOf(1, 3, 2, 4, 5),
            listOf(8, 6, 4, 4, 1),
            listOf(1, 3, 6, 7, 9),
        )

        assertEquals(4, Task2.solve(paths))
    }

    @Test
    fun `should successfully solve the real task`() {
        val list = File("src/test/resources/year_2024/day_02/TaskData.txt").readLines()
            .map { it.split(" ").map(String::toInt) }
        println("Task solution: ${Task2.solve(list)}")
    }
}