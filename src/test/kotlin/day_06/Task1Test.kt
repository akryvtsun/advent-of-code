package day_06

import org.junit.jupiter.api.Test
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

}