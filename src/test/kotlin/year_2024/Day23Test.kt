package year_2024

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import year_2024.day_23.Task1
import year_2024.day_23.Task2
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 23: LAN Party")
class Day23Test {

    private val input = """
        kh-tc
        qp-kh
        de-cg
        ka-co
        yn-aq
        qp-ub
        cg-tb
        vc-aq
        tb-ka
        wh-tc
        yn-cg
        kh-ub
        ta-co
        de-co
        tc-td
        tb-wq
        wh-td
        ta-ka
        td-qp
        aq-cg
        wq-ub
        ub-vc
        de-ta
        wq-aq
        wq-vc
        wh-yn
        ka-de
        kh-ta
        co-tc
        wh-qp
        tb-vc
        td-yn
    """.trimIndent()

    private fun transform(input: String): List<Pair<String, String>> {
        return input.lines()
            .map { link ->
                val (left, right) = link.split("-")
                left to right
            }
    }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val data = transform(input)
            assertEquals(7, Task1.solve(data))
        }

        @Test
        fun `Actual answer`() {
            val input = File("src/test/resources/year_2024/day_23/TaskData.txt").readText()
            val data = transform(input)
            assertEquals(1308, Task1.solve(data))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val data = transform(input)
            assertEquals("co,de,ka,ta", Task2.solve(data))
        }

        @Test
        fun `Actual answer`() {
            val input = taskData(2024, 23).readText()
            val data = transform(input)
            assertEquals("bu,fq,fz,pn,rr,st,sv,tr,un,uy,zf,zi,zy", Task2.solve(data))
        }
    }
}