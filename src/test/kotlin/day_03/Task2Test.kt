package day_03

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task2Test {

    @Test
    fun `should successfully pass task example`() {
        assertEquals(48, Task2.solve("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"))
    }

    @Test
    fun `should successfully solve the real task`() {
        File("src/test/resources/day_03/TaskData.txt")
            .readText()
            .also { println("Task solution: ${Task2.solve(it)}") }   // 83158140
    }
}