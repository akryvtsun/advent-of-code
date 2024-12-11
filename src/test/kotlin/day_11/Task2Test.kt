package day_11

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task2Test {

    companion object {
        fun transform(input: String): List<Long> = input.split(" ").map { it.toLong() }
    }

    @Test
    fun `should successfully solve the real task`() {
        val input = File("src/test/resources/day_11/TaskData.txt").readText()
        val data = transform(input)
        assertEquals(-1, Task1.solve(data, 75))
    }
}