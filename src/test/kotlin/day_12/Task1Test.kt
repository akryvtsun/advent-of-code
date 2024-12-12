package day_12

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task1Test {

    @Test
    fun `should successfully pass task example 1`() {
        val map = """
            AAAA
            BBCD
            BBCC
            EEEC
        """.trimIndent()
        val input = transform(map)
        assertEquals(140, Task1.solve(input))
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
        assertEquals(772, Task1.solve(input))
    }

    @Test
    fun `should successfully pass task example 3`() {
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
        assertEquals(1930, Task1.solve(input))
    }

    @Test
    fun `should successfully solve the real task`() {
        val map = File("src/test/resources/day_12/TaskData.txt").readText()
        val input = transform(map)
        assertEquals(-1, Task1.solve(input))
    }
}