package year_2024.day_06

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task1Test {

    @Test
    fun `should successfully pass task example`() {
        val map =  """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
        """.trimIndent()
        assertEquals(41, Task1.solve(map.split("\n").map { StringBuilder(it) }))
    }

    @Test
    fun `should successfully solve the real task`() {
        val map = File("src/test/resources/year_2024/day_06/TaskData.txt").readLines().map { StringBuilder(it) }
        println("Task solution: ${Task1.solve(map)}")   // 4433
    }
}