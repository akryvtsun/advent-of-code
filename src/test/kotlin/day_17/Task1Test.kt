package day_17

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task1Test {

    @Test
    fun `should successfully pass task example`() {
        val config = """
            Register A: 729
            Register B: 0
            Register C: 0
            
            Program: 0,1,5,4,3,0
        """.trimIndent()
        val input = transform(config)
        assertEquals("4,6,3,5,6,3,5,2,1,0", Task1.solve(input))
    }

    @Test
    fun `should successfully solve the real task`() {
        val map = File("src/test/resources/day_17/TaskData.txt").readText()
        val input = transform(map)
        assertEquals("", Task1.solve(input))
    }
}