package day_04

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task2Test {

    @Test
    fun `should successfully pass task example`() {
        val ex1 = listOf(
            "M.S",
            ".A.",
            "M.S",
        )
        assertEquals(1, Task2.solve(ex1))

        val ex2 = listOf(
            ".M.S......",
            "..A..MSMS.",
            ".M.S.MAA..",
            "..A.ASMSM.",
            ".M.S.M....",
            "..........",
            "S.S.S.S.S.",
            ".A.A.A.A..",
            "M.M.M.M.M.",
            "..........",
        )
        assertEquals(9, Task2.solve(ex2))
    }

    @Test
    fun `should successfully solve the real task`() {
        File("src/test/resources/day_04/TaskData.txt")
            .readLines()
            .also { println("Task solution: ${Task2.solve(it)}") }
    }
}