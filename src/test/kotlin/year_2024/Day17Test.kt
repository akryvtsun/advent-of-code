package year_2024

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import year_2024.day_17.Computer
import year_2024.day_17.Task1
import year_2024.day_17.Task2
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 17: Chronospatial Computer")
class Day17Test {

    fun transform(input: String): Computer {
        val blocks = input.split("\n\n")
        val regs = blocks[0].lines()
        val A =  register(regs[0])
        val B =  register(regs[1])
        val C =  register(regs[2])
        val program = blocks[1].substringAfterLast(" ").split(",").map { it.toInt() }
        return Computer(A, B, C, program)
    }

    private fun register(data: String) = data.substringAfterLast(" ").toInt()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @org.junit.jupiter.api.Test
        fun `should successfully pass task example`() {
            val config = """
                Register A: 729
                Register B: 0
                Register C: 0
                
                Program: 0,1,5,4,3,0
            """.trimIndent()
            val input = transform(config)
            assertEquals("4,6,3,5,6,3,5,2,1,0", Task1.solve(input).second)
        }

        @org.junit.jupiter.api.Test
        fun `should successfully pass task 2 example`() {
            val config = """
                Register A: 117440
                Register B: 0
                Register C: 0
                
                Program: 0,3,5,4,3,0
            """.trimIndent()
            val input = transform(config)
            assertEquals("0,3,5,4,3,0", Task1.solve(input).second)
        }

        @org.junit.jupiter.api.Test
        fun `test case 1`() {
            assertEquals(1, Task1.solve(Computer(0, 0, 9, listOf(2, 6))).first.B)
        }

        @org.junit.jupiter.api.Test
        fun `test case 2`() {
            assertEquals("0,1,2", Task1.solve(Computer(10, 0, 0, listOf(5, 0, 5, 1, 5, 4))).second)
        }

        @org.junit.jupiter.api.Test
        fun `test case 3`() {
            assertEquals("4,2,5,6,7,7,7,7,3,1,0", Task1.solve(Computer(2024, 0, 0, listOf(0, 1, 5, 4, 3, 0))).second)
        }

        @org.junit.jupiter.api.Test
        fun `test case 4`() {
            assertEquals(26, Task1.solve(Computer(0, 29, 0, listOf(1, 7))).first.B)
        }

        @org.junit.jupiter.api.Test
        fun `test case 5`() {
            assertEquals(44354, Task1.solve(Computer(0, 2024, 43690, listOf(4, 0))).first.B)
        }

        @Test
        fun `Actual answer`() {
            val map = taskData(2024, 17).readText()
            val input = transform(map)
            assertEquals("7,5,4,3,4,5,3,4,6", Task1.solve(input).second)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            assertEquals(117440, Task2.solve(listOf(0, 3, 5, 4, 3, 0)))
        }

        @Test
        fun `Actual answer`() {
            val map = taskData(2024, 17).readText()
            val input = transform(map)
            assertEquals(164278899142333, Task2.solve(input.program))
        }
    }
}