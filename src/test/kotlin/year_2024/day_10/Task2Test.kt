package year_2024.day_10

import org.junit.jupiter.api.Test
import year_2024.day_10.IslandMap.Companion.transform
import year_2024.day_10.Task2.Companion.getRate
import java.io.File
import kotlin.test.assertEquals

class Task2Test {

    @Test
    fun `check rate function`(){
        val input =  """
            .....0.
            ..4321.
            ..5..2.
            ..6543.
            ..7..4.
            ..8765.
            ..9....
        """.trimIndent()
        val data = transform(input)
        assertEquals(3, data.getRate(Point(0, 5)))
    }

    @Test
    fun `check rate function 2`(){
        val input =  """
            ..90..9
            ...1.98
            ...2..7
            6543456
            765.987
            876....
            987....
        """.trimIndent()
        val data = transform(input)
        assertEquals(13, data.getRate(Point(0, 3)))
    }

    @Test
    fun `check rate function 3`(){
        val input =  """
            012345
            123456
            234567
            345678
            4.6789
            56789.
        """.trimIndent()
        val data = transform(input)
        assertEquals(227, data.getRate(Point(0, 0)))
    }

    @Test
    fun `should successfully pass task example`() {
        val input =  """
            89010123
            78121874
            87430965
            96549874
            45678903
            32019012
            01329801
            10456732
        """.trimIndent()
        val data = transform(input)
        assertEquals(81, Task2.solve(data))
    }

    @Test
    fun `should successfully solve the real task`() {
        val input = File("src/test/resources/year_2024/day_10/TaskData.txt").readText()
        val data = transform(input)
        assertEquals(1017, Task2.solve(data))
    }
}