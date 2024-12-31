package year_2024.day_06

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.time.measureTimedValue

class Task2Test {

    @Test
    fun `should successfully pass task example`() {
        val input = """
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

        doTest(6, input)
    }

    @Test
    fun `should successfully solve the real task`() {
        val input = File("src/test/resources/year_2024/day_06/TaskData.txt").readText()

        doTest(1516, input)
    }

    private fun doTest(expected: Int, input: String) {
        var initPos: Position? = null
        val obstacles = mutableListOf<Position>()
        var width: Int? = null
        val lines = input.split('\n')
        var height = lines.size
        lines.forEachIndexed { i, line ->
            line.forEachIndexed { j, char ->
                when (char) {
                    '#' -> obstacles.add(Position(i, j))
                    '^' -> initPos = Position(i, j)
                }
            }
            width = line.length
        }

        val actual = measureTimedValue { Task2.solve(height, width!!, obstacles, initPos!!, Direction.UP) }
        println("Execution time: ${actual.duration}")
        assertEquals(expected, actual.value)
    }
}