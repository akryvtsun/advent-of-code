package year_2024.day_10

import org.junit.jupiter.api.Test
import year_2024.day_10.IslandMap.Companion.transform
import year_2024.day_10.Task1.Companion.getScore
import java.io.File
import kotlin.test.assertEquals

class Task1Test {

    @Test
    fun `check score function`(){
        val input =  """
            ...0...
            ...1...
            ...2...
            6543456
            7.....7
            8.....8
            9.....9
        """.trimIndent()
        val data = transform(input)
        assertEquals(2, data.getScore(Point(0, 3)))
    }

    @Test
    fun `check score function 2`(){
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
        assertEquals(4, data.getScore(Point(0, 3)))
    }

    @Test
    fun `check score function 3`(){
        val input =  """
            10..9..
            2...8..
            3...7..
            4567654
            ...8..3
            ...9..2
            .....01
        """.trimIndent()
        val data = transform(input)
        assertEquals(1, data.getScore(Point(0, 1)))
        assertEquals(2, data.getScore(Point(6, 5)))
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
        assertEquals(36, Task1.solve(data))
    }

    @Test
    fun `should successfully solve the real task`() {
        val input = File("src/test/resources/year_2024/day_10/TaskData.txt").readText()
        val data = transform(input)
        assertEquals(501, Task1.solve(data))
    }
}