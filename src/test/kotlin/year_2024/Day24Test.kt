package year_2024

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import year_2024.day_24.Task1
import year_2024.day_24.Task2
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 19: Linen Layout")
class Day24Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @org.junit.jupiter.api.Test
        fun `should successfully pass task example 1`() {
            val input = """
                x00: 1
                x01: 1
                x02: 1
                y00: 0
                y01: 1
                y02: 0
                
                x00 AND y00 -> z00
                x01 XOR y01 -> z01
                x02 OR y02 -> z02
            """.trimIndent()
            val (init, links) = Task1.transform(input)
            assertEquals(4, Task1.solve(init, links))
        }

        @org.junit.jupiter.api.Test
        fun `should successfully pass task example 2`() {
            val input = """
                x00: 1
                x01: 0
                x02: 1
                x03: 1
                x04: 0
                y00: 1
                y01: 1
                y02: 1
                y03: 1
                y04: 1
                
                ntg XOR fgs -> mjb
                y02 OR x01 -> tnw
                kwq OR kpj -> z05
                x00 OR x03 -> fst
                tgd XOR rvg -> z01
                vdt OR tnw -> bfw
                bfw AND frj -> z10
                ffh OR nrd -> bqk
                y00 AND y03 -> djm
                y03 OR y00 -> psh
                bqk OR frj -> z08
                tnw OR fst -> frj
                gnj AND tgd -> z11
                bfw XOR mjb -> z00
                x03 OR x00 -> vdt
                gnj AND wpb -> z02
                x04 AND y00 -> kjc
                djm OR pbm -> qhw
                nrd AND vdt -> hwm
                kjc AND fst -> rvg
                y04 OR y02 -> fgs
                y01 AND x02 -> pbm
                ntg OR kjc -> kwq
                psh XOR fgs -> tgd
                qhw XOR tgd -> z09
                pbm OR djm -> kpj
                x03 XOR y03 -> ffh
                x00 XOR y04 -> ntg
                bfw OR bqk -> z06
                nrd XOR fgs -> wpb
                frj XOR qhw -> z04
                bqk OR frj -> z07
                y03 OR x01 -> nrd
                hwm AND bqk -> z03
                tgd XOR rvg -> z12
                tnw OR pbm -> gnj
            """.trimIndent()
            val (init, links) = Task1.transform(input)
            assertEquals(2024, Task1.solve(init, links))
        }

        @Test
        fun `Actual answer`() {
            val input = readTaskData(2024, 24)
            val (init, links) = Task1.transform(input)
            assertEquals(48063513640678, Task1.solve(init, links))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Disabled("Correct result was obtained in semi-manual mode")
        @Test
        fun `Actual answer`() {
            val input = readTaskData(2024, 24)
            val gates = Task2.transform(input)
            assertEquals("hqh,mmk,pvb,qdq,vkq,z11,z24,z38", Task2.solve(gates))
        }
    }
}