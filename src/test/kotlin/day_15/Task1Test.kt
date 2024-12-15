package day_15

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task1Test {

    @Test
    fun `should successfully pass task example`() {
        val config = """
            p=0,4 v=3,-3
            p=6,3 v=-1,-3
            p=10,3 v=-1,2
            p=2,0 v=2,-1
            p=0,0 v=1,3
            p=3,0 v=-2,-2
            p=7,6 v=-1,-3
            p=3,0 v=-1,-2
            p=9,3 v=2,3
            p=7,3 v=-1,2
            p=2,4 v=2,-3
            p=9,5 v=-3,-3
        """.trimIndent()
        val input = transform(config)
        assertEquals(12, Task1.solve(7, 11, input))
    }

    @Test
    fun `should successfully solve the real task`() {
        val map = File("src/test/resources/day_14/TaskData.txt").readText()
        val input = transform(map)
        assertEquals(230461440, Task1.solve(103, 101, input))
    }
}