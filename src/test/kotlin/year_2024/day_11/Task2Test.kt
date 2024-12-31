package year_2024.day_11

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task2Test {

    @Test
    fun `should successfully pass task example`() {
        val input = transform("125 17")
        assertEquals(22, Task2.solve(input, 6))
        assertEquals(55312, Task2.solve(input, 25))
    }

    @Test
    fun `should successfully solve task 1`() {
        val input = File("src/test/resources/year_2024/day_11/TaskData.txt").readText()
        val data = transform(input)
        assertEquals(217812, Task2.solve(data, 25))
    }

    @Test
    fun `should successfully solve the real task`() {
        val input = File("src/test/resources/year_2024/day_11/TaskData.txt").readText()
        val data = transform(input)
        assertEquals(259112729857522, Task2.solve(data, 75))
    }
}