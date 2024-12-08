package day_08

import day_06.Position
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task1Test {

    @Test
    fun `should successfully pass task example`() {
        val input =  """
            ............
            ........0...
            .....0......
            .......0....
            ....0.......
            ......A.....
            ............
            ............
            ........A...
            .........A..
            ............
            ............
        """.trimIndent()
        val data = transform(input)
        assertEquals(14, Task1.solve(data))
    }

    private fun transform(input: String): List<Antenna> {
        return input.split("\n")
            .flatMapIndexed() { i, line ->
                val lineList = mutableListOf<Antenna>()
                for (j in line.indices) {
                    if (line[j] != '.') {
                        lineList += Antenna(Position(i, j), line[j])
                    }
                }
                lineList
            }
    }

    @Test
    fun `should successfully solve the real task`() {
        val input = File("src/test/resources/day_07/TaskData.txt").readText()
        val data = transform(input)
        assertEquals(-1, Task1.solve(data))
    }
}