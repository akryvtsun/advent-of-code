package day_24

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.Ignore
import kotlin.test.assertEquals

class Task2Test {

    @Test
    @Ignore
    fun `should successfully solve the real task`() {
        val input = File("src/test/resources/day_24/TaskData.txt").readText()
        val gates = Task2.transform(input)
        assertEquals("hqh,mmk,pvb,qdq,vkq,z11,z24,z38", Task2.solve(gates))
    }
}