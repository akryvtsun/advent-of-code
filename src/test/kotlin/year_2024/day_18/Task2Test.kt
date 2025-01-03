package year_2024.day_18

import org.junit.jupiter.api.Test
import year_2024.Point
import java.io.File
import kotlin.test.assertEquals

class Task2Test {

    @Test
    fun `should successfully pass task example`() {
        val input = """
            5,4
            4,2
            4,5
            3,0
            2,1
            6,3
            2,4
            1,5
            0,6
            3,3
            2,6
            5,1
            1,2
            5,5
            2,5
            6,5
            1,4
            0,4
            6,4
            1,1
            6,1
            1,0
            0,5
            1,6
            2,0
        """.trimIndent()
        val bytes = transform(input)
        assertEquals(Point(6,1), Task2.solve(bytes, 7, 7))
    }

    @Test
    fun `should successfully solve the real task`() {
        val input = File("src/test/resources/year_2024/day_18/TaskData.txt").readText()
        val bytes = transform(input)
        assertEquals(Point(50,28), Task2.solve(bytes, 71, 71))
    }
}