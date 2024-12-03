package day_03

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task1Test {

    @Test
    fun `should successfully pass task example`() {
        assertEquals(0, Task1.solve("mul(4*, mul(6,9!, ?(12,34),"))
        assertEquals(0, Task1.solve("mul ( 2 , 4 )"))
        assertEquals(161, Task1.solve("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"))
    }

    @Test
    fun `should successfully solve the real task`() {
        File("src/test/resources/day_03/TaskData.txt")
            .readText()
            .also { println("Task solution: ${Task1.solve(it)}") }
    }
}