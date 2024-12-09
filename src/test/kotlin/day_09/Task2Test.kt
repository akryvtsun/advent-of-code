package day_09

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task2Test {

    @Test
    fun `should successfully pass task example`() {
        assertEquals(2858, Task2.solve("2333133121414131402"))
    }

    @Test
    fun `should successfully solve the real task`() {
        val input = File("src/test/resources/day_09/TaskData.txt").readText()
        assertEquals(6423258376982, Task2.solve(input))
    }
}