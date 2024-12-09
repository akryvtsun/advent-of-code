package day_09

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task1Test {

    @Test
    fun `should successfully pass task example`() {
        assertEquals(1928, Task1.solve("2333133121414131402"))
    }

    @Test
    fun `should successfully solve the real task`() {
        val input = File("src/test/resources/day_09/TaskData.txt").readText()
        assertEquals(-1, Task1.solve(input))
    }
}