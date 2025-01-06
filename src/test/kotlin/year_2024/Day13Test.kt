package year_2024

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import year_2024.day_13.Point
import year_2024.day_13.SlotMachine
import year_2024.day_13.Task1
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 13: Claw Contraption")
class Day13Test {

    fun transform(input: String, shift: Long = 0) = input.split("\n\n")
        .map { config ->
            val (a, b, p) = config.lines()
            // Button A
            val (dAx, dAy) = """Button A: X\+(\d\d), Y\+(\d\d)""".toRegex().find(a)!!.destructured
            val dA = Point(dAy.toLong(), dAx.toLong())
            // Button B
            val (dBx, dBy) = """Button B: X\+(\d\d), Y\+(\d\d)""".toRegex().find(b)!!.destructured
            val dB = Point(dBy.toLong(), dBx.toLong())
            // Prize
            val (px, py) = """Prize: X=(\d+), Y=(\d+)""".toRegex().find(p)!!.destructured
            val prize = Point(py.toLong() + shift, px.toLong() + shift)
            SlotMachine(dA, dB, prize)
        }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val config = """
                Button A: X+94, Y+34
                Button B: X+22, Y+67
                Prize: X=8400, Y=5400
                
                Button A: X+26, Y+66
                Button B: X+67, Y+21
                Prize: X=12748, Y=12176
                
                Button A: X+17, Y+86
                Button B: X+84, Y+37
                Prize: X=7870, Y=6450
                
                Button A: X+69, Y+23
                Button B: X+27, Y+71
                Prize: X=18641, Y=10279
            """.trimIndent()
            val input = transform(config)
            assertEquals(480, Task1.solve(input))
        }

        @Test
        fun `Actual answer`() {
            val map = taskData(2024, 13).readText()
            val input = transform(map)
            assertEquals(28138, Task1.solve(input))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val config = """
                Button A: X+94, Y+34
                Button B: X+22, Y+67
                Prize: X=10000000008400, Y=10000000005400
                
                Button A: X+26, Y+66
                Button B: X+67, Y+21
                Prize: X=10000000012748, Y=10000000012176
                
                Button A: X+17, Y+86
                Button B: X+84, Y+37
                Prize: X=10000000007870, Y=10000000006450
                
                Button A: X+69, Y+23
                Button B: X+27, Y+71
                Prize: X=10000000018641, Y=10000000010279
            """.trimIndent()
            val input = transform(config)
            assertEquals(875318608908, Task1.solve(input))
        }

        @Test
        fun `Actual answer`() {
            val map = taskData(2024, 13).readText()
            val input = transform(map, 10000000000000)
            assertEquals(108394825772874, Task1.solve(input))
        }
    }
}