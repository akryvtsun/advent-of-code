package day_24

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task2Test {

    @Test
    fun `should successfully solve the real task`() {
        val input = File("src/test/resources/day_24/TaskData.txt").readText()
        val gates = Task2.transform(input)
        assertEquals("", Task2.solve(gates))
    }
}