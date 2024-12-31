package year_2024.day_12

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task2Test {

    @Test
    fun `should successfully pass task example 1`() {
        val map = """
            AAAA
            BBCD
            BBCC
            EEEC
        """.trimIndent()
        val input = transform(map)
        assertEquals(80, Task2.solve(input))
    }

    @Test
    fun `should successfully pass task example 2`() {
        val map = """
            OOOOO
            OXOXO
            OOOOO
            OXOXO
            OOOOO
        """.trimIndent()
        val input = transform(map)
        assertEquals(436, Task2.solve(input))
    }

    @Test
    fun `should successfully pass task example 3`() {
        val map = """
            EEEEE
            EXXXX
            EEEEE
            EXXXX
            EEEEE
        """.trimIndent()
        val input = transform(map)
        assertEquals(236, Task2.solve(input))
    }

    @Test
    fun `should successfully pass task example 4`() {
        val map = """
            AAAAAA
            AAABBA
            AAABBA
            ABBAAA
            ABBAAA
            AAAAAA
        """.trimIndent()
        val input = transform(map)
        assertEquals(368, Task2.solve(input))
    }

    @Test
    fun `should successfully pass task example 5`() {
        val map = """
            RRRRIICCFF
            RRRRIICCCF
            VVRRRCCFFF
            VVRCCCJFFF
            VVVVCJJCFE
            VVIVCCJJEE
            VVIIICJJEE
            MIIIIIJJEE
            MIIISIJEEE
            MMMISSJEEE
        """.trimIndent()
        val input = transform(map)
        assertEquals(1206, Task2.solve(input))
    }

    @Test
    // My algorithm returns incorrect result
    fun `should successfully solve the real task`() {
        val map = File("src/test/resources/year_2024/day_12/TaskData.txt").readText()
        val input = transform(map)
        assertEquals(921636, Task2.solve(input))
    }
}