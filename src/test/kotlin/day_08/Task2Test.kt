package day_08

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task2Test {

    @Test
    fun `should successfully pass task example`() {
        val input =  """
            ............
            ........0...
            .....0......
            .......0....
            ....0.......
            ......A.....
            ............
            ............
            ........A...
            .........A..
            ............
            ............
        """.trimIndent()
        val data = Task1Test.transform(input)
        assertEquals(34, Task2.solve(data))
    }

    @Test
    fun `should successfully solve the real task`() {
        val input = File("src/test/resources/day_08/TaskData.txt").readText()
        val data = Task1Test.transform(input)
        assertEquals(1352, Task2.solve(data))
    }
}