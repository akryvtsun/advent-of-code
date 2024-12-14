package day_14

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task2Test {

    @Test
    fun `should successfully solve the real task`() {
        val map = File("src/test/resources/day_14/TaskData.txt").readText()
        val input = transform(map)
        assertEquals(-1, Task2.solve(103, 101, input))
    }
}