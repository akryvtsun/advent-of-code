package day_13

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task2Test {

    @Test
    fun `should successfully pass task example`() {
        val config = """
            Button A: X+94, Y+34
            Button B: X+22, Y+67
            Prize: X=10000000008400, Y=10000000005400
            
            Button A: X+26, Y+66
            Button B: X+67, Y+21
            Prize: X=10000000012748, Y=10000000012176
            
            Button A: X+17, Y+86
            Button B: X+84, Y+37
            Prize: X=10000000007870, Y=10000000006450
            
            Button A: X+69, Y+23
            Button B: X+27, Y+71
            Prize: X=10000000018641, Y=10000000010279
        """.trimIndent()
        val input = transform(config)
        assertEquals(875318608908, Task2.solve(input))
    }

    @Test
    fun `should successfully solve the real task`() {
        val map = File("src/test/resources/day_13/TaskData.txt").readText()
        val input = transform(map, 10000000000000)
        assertEquals(108394825772874, Task2.solve(input))
    }
}