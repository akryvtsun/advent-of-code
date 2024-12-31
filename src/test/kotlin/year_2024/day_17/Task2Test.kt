package year_2024.day_17

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task2Test {

    @Test
    fun `should successfully pass task example`() {
        assertEquals(117440, Task2.solve(listOf(0, 3, 5, 4, 3, 0)))
    }

    @Test
    fun `should successfully solve the real task`() {
        val map = File("src/test/resources/year_2024/day_17/TaskData.txt").readText()
        val input = transform(map)
        assertEquals(164278899142333, Task2.solve(input.program))
    }
}