package day_19

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task1Test {

    @Test
    fun `should successfully pass task example`() {
        val input = """
            r, wr, b, g, bwu, rb, gb, br
            
            brwrr
            bggr
            gbbr
            rrbgbr
            ubwu
            bwurrg
            brgr
            bbrgwb
        """.trimIndent()
        val (patterns, designs) = transform(input)
        assertEquals(6, Task1.solve(patterns, designs))
    }

    @Test
    fun `should successfully solve the real task`() {
        val input = File("src/test/resources/day_19/TaskData.txt").readText()
        val (patterns, designs) = transform(input)
        assertEquals(338, Task1.solve(patterns, designs))
    }
}