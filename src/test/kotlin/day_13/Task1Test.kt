package day_13

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task1Test {

    @Test
    fun `should successfully pass task example`() {
        val config = """
            Button A: X+94, Y+34
            Button B: X+22, Y+67
            Prize: X=8400, Y=5400
            
            Button A: X+26, Y+66
            Button B: X+67, Y+21
            Prize: X=12748, Y=12176
            
            Button A: X+17, Y+86
            Button B: X+84, Y+37
            Prize: X=7870, Y=6450
            
            Button A: X+69, Y+23
            Button B: X+27, Y+71
            Prize: X=18641, Y=10279
        """.trimIndent()
        val input = transform(config)
        assertEquals(480, Task1.solve(input))
    }

    @Test
    fun `should successfully solve the real task`() {
        val map = File("src/test/resources/day_13/TaskData.txt").readText()
        val input = transform(map)
        assertEquals(-1, Task1.solve(input))
    }
}