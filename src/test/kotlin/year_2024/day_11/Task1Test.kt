package year_2024.day_11

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task1Test {

    @Test
    fun `test blink function`(){
        assertEquals(listOf<Long>(1, 2024, 1, 0, 9, 9, 2021976), Task1.blink(listOf(0, 1, 10, 99, 999)))
    }

    @Test
    fun `should successfully pass task example`() {
        val input = transform("125 17")
        assertEquals(22, Task1.solve(input, 6))
        assertEquals(55312, Task1.solve(input, 25))
    }

    @Test
    fun `should successfully solve the real task`() {
        val input = File("src/test/resources/year_2024/day_11/TaskData.txt").readText()
        val data = transform(input)
        assertEquals(217812, Task1.solve(data, 25))
    }
}