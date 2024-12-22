package day_21

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task1Test {

    @Test
    fun `should successfully pass task example`() {
        val input = """
            029A
            980A
            179A
            456A
            379A
        """.trimIndent()
        val data = input.lines()
        assertEquals(126384, Task1.solve(data))
    }

    @Test
    fun `should successfully solve the real task`() {
        val input = File("src/test/resources/day_21/TaskData.txt").readText()
        val data = input.lines()
        assertEquals(-1, Task1.solve(data))
    }
}