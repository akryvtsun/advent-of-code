package day_04

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task1Test {

    @Test
    fun `should successfully pass task example`() {
        val ex1 = listOf(
            "..X...",
            ".SAMX.",
            ".A..A.",
            "XMAS.S",
            ".X....",
        )
        assertEquals(4, Task1.solve(ex1))

        val ex2 = listOf(
            "MMMSXXMASM",
            "MSAMXMSMSA",
            "AMXSXMAAMM",
            "MSAMASMSMX",
            "XMASAMXAMM",
            "XXAMMXXAMA",
            "SMSMSASXSS",
            "SAXAMASAAA",
            "MAMMMXMMMM",
            "MXMXAXMASX",
        )
        assertEquals(18, Task1.solve(ex2))
    }

    @Test
    fun `should successfully solve the real task`() {
        File("src/test/resources/day_04/TaskData.txt")
            .readLines()
            .also { println("Task solution: ${Task1.solve(it)}") }
    }
}