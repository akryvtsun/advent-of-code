package year_2024

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import year_2024.Day21.Companion.directionalKeypad
import year_2024.Day21.Companion.numericKeypad
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 21: Keypad Conundrum")
class Day21Test {

    companion object {
        val testInput = """
            029A
            980A
            179A
            456A
            379A
        """.trimIndent()

        val realInput = TaskData(2024, 21).asString()

        @JvmStatic
        fun paths() = listOf(
            Arguments.of("029A", "<vA<AA>>^AvAA<^A>A<v<A>>^AvA^A<vA>^A<v<A>^A>AAvA^A<v<A>A>^AAAvA<^A>A"),
            Arguments.of("980A", "<v<A>>^AAAvA^A<vA<AA>>^AvAA<^A>A<v<A>A>^AAAvA<^A>A<vA>^A<A>A"),
            Arguments.of("179A", "<v<A>>^A<vA<A>>^AAvAA<^A>A<v<A>>^AAvA^A<vA>^AA<A>A<v<A>A>^AAAvA<^A>A"),
            Arguments.of("456A", "<v<A>>^AA<vA<A>>^AAvAA<^A>A<vA>^A<A>A<vA>^A<A>A<v<A>A>^AAvA<^A>A"),
            Arguments.of("379A", "<v<A>>^AvA^A<vA<AA>>^AAvA<^A>AAvA^A<vA>^AA<A>A<v<A>A>^AAAvA<^A>A"),
        )

        @JvmStatic
        fun part1Data() = listOf(
            arguments(testInput, 126384),
            arguments(realInput, 203814)
        )

        @JvmStatic
        fun part2Data() = listOf(
            arguments(realInput, 248566068436630)
        )
    }

    @Test
    fun `should successfully find path for 029A`() {
        val path1 = "<A^A>^^AvvvA"
        assertEquals(
            path1.length,   // 12
            Day21.keypadPaths(setOf("029A"), numericKeypad).first().length)

        val path2 = "v<<A>>^A<A>AvA<^AA>A<vAAA>^A"
        assertEquals(
            path2.length,   // 28
            Day21.keypadPaths(setOf(path1), directionalKeypad).first().length)

        val path3 = "<vA<AA>>^AvAA<^A>A<v<A>>^AvA^A<vA>^A<v<A>^A>AAvA^A<v<A>A>^AAAvA<^A>A"
        assertEquals(
            path3.length,   // 68
            Day21.keypadPaths(setOf(path2), directionalKeypad).first().length)
    }

    @ParameterizedTest
    @MethodSource("paths")
    fun `should successfully find shortest path`(code: String, path: String) {
        assertEquals(path.length, Day21.shortestPath(code).length)
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: String, result: Int) {
        assertThat(
            Day21(input).solvePart1()
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: String,result: Long) {
        assertThat(
            Day21(input).solvePart2()
        ).isEqualTo(result)
    }
}