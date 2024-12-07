package day_06

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Task2Test {

    @Test
    fun `should successfully pass task example`() {
        val map = """
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

        var initPos: Task2.Position? = null
        val obstacles = mutableListOf<Task2.Position>()
        var width: Int? = null
        val lines = map.split('\n')
        var height = lines.size
        lines.forEachIndexed { i, line ->
            line.forEachIndexed { j, char ->
                when (char) {
                    '#' -> obstacles.add(Task2.Position(i, j))
                    '^' -> initPos = Task2.Position(i, j)
                }
            }
            width = line.length
        }

        assertEquals(6, Task2.solve(height, width!!, obstacles, initPos!!, Task2.Direction.UP))
    }

//    @Test
//    fun `should successfully solve the real task`() {
//        val map = File("src/test/resources/day_06/TaskData.txt").readLines().map {
//            buildList {
//                for (c in it) {
//                    add(mutableSetOf(c))
//                }
//            }
//        }
//        println("Task solution: ${Task2.solve(map)}")   // 4433
//    }
}