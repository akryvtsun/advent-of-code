package year_2024

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import taskData
import year_2024.day_08.Antenna
import year_2024.day_08.Data
import year_2024.day_08.Position
import year_2024.day_08.Task1
import year_2024.day_08.Task2
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 8: Resonant Collinearity")
class Day08Test {

    val input = """
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

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val data = transform(input)
            assertEquals(14, Task1.solve(data))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 8).readText()
            val data = transform(input)
            assertEquals(376, Task1.solve(data))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val data = transform(input)
            assertEquals(34, Task2.solve(data))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 8).readText()
            val data = transform(input)
            assertEquals(1352, Task2.solve(data))
        }
    }
}