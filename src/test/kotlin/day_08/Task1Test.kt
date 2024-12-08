package day_08

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task1Test {

    companion object {
        fun transform(input: String): Data {
            val lines = input.split("\n")
            val height = lines.size
            val width = lines.first().length
            val antennas = lines.flatMapIndexed { i, line ->
                val lineList = mutableListOf<Antenna>()
                for (j in line.indices) {
                    if (line[j] != '.') {
                        lineList += Antenna(Position(i, j), line[j])
                    }
                }
                lineList
            }
            return Data(height, width, antennas)
        }
    }

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

    @Test
    fun `should successfully solve the real task`() {
        val input = File("src/test/resources/day_08/TaskData.txt").readText()
        val data = transform(input)
        assertEquals(376, Task1.solve(data))
    }
}