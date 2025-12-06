package year_2024

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 23: LAN Party")
class Day23Test {
    companion object {
        val testInput = """
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

        val realInput = TaskData(2024, 23).asString()

        @JvmStatic
        fun part1Data() = listOf(
            arguments(testInput, 7),
            arguments(realInput, 1308)
        )

        @JvmStatic
        fun part2Data() = listOf(
            arguments(testInput, "co,de,ka,ta"),
            arguments(realInput, "bu,fq,fz,pn,rr,st,sv,tr,un,uy,zf,zi,zy")
        )
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: String, result: Long) {
        assertThat(
            Day23(input).solvePart1()
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: String, result: String) {
        assertThat(
            Day23(input).solvePart2()
        ).isEqualTo(result)
    }
}