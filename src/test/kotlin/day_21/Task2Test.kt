package day_21

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task2Test {

    @Test
    fun `should successfully solve the real task`() {
        val input = File("src/test/resources/day_21/TaskData.txt").readText()
        val data = input.lines()
        assertEquals(-1, Task2.solve(data))
    }
}