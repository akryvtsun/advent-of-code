package day_17

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task2Test {

    @Test
    fun `should successfully pass task example`() {
        val config = """
            Register A: 117440
            Register B: 0
            Register C: 0
            
            Program: 0,3,5,4,3,0
        """.trimIndent()
        val input = transform(config)
        assertEquals("0,3,5,4,3,0", Task2.solve(input).second)
    }

    @Test
    fun `test case 1`() {
        assertEquals(1, Task2.solve(Computer(0, 0, 9, listOf(2, 6))).first.B)
    }

    @Test
    fun `test case 2`() {
        assertEquals("0,1,2", Task2.solve(Computer(10, 0, 0, listOf(5, 0, 5, 1, 5, 4))).second)
    }

    @Test
    fun `test case 3`() {
        assertEquals("4,2,5,6,7,7,7,7,3,1,0", Task2.solve(Computer(2024, 0, 0, listOf(0, 1, 5, 4, 3, 0))).second)
    }

    @Test
    fun `test case 4`() {
        assertEquals(26, Task2.solve(Computer(0, 29, 0, listOf(1, 7))).first.B)
    }

    @Test
    fun `test case 5`() {
        assertEquals(44354, Task2.solve(Computer(0, 2024, 43690, listOf(4, 0))).first.B)
    }

    @Test
    fun `should successfully solve the real task`() {
        val map = File("src/test/resources/day_17/TaskData.txt").readText()
        val input = transform(map)
        assertEquals("", Task2.solve(input).second)
    }
}