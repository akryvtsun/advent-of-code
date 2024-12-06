package day_06

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task2Test {

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
        assertEquals(6, Task2.solve(map.split("\n").map {
            buildList {
                for (c in it) {
                    add(mutableSetOf(c))
                }
            }
        }))
    }

    @Test
    fun `should successfully solve the real task`() {
        val map = File("src/test/resources/day_06/TaskData.txt").readLines().map {
            buildList {
                for (c in it) {
                    add(mutableSetOf(c))
                }
            }
        }
        println("Task solution: ${Task2.solve(map)}")   // 4433
    }
}