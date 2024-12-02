package day_02

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task1Test {

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

        assertEquals(2, Task1.solve(paths))
    }

    @Test
    fun `should successfully solve the real task`() {
        val list = mutableListOf<List<Int>>()
        File("src/test/resources/day_02/TaskData.txt").forEachLine { line ->
            val path = line.split(" ")
                .map { it.toInt() }
            list.add(path)
        }
        println("Task solution: ${Task1.solve(list)}")
    }
}