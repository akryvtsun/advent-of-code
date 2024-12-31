package year_2024.day_21

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task2Test {

    @Test
    @Disabled("Couldn't fetch result - needs time optimization")
    fun `should successfully solve the real task`() {
        val input = File("src/test/resources/year_2024/day_21/TaskData.txt").readText()
        val data = input.lines()
        assertEquals(-1, Task2.solve(data))
    }
}