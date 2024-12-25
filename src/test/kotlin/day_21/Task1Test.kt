package day_21

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import kotlin.test.assertEquals

class Task1Test {

    @Test
    fun `should successfully find path for 029A`() {
        val path1 = "<A^A>^^AvvvA"
        assertEquals(
            path1.length,   // 12
            Task1.keypadPaths(setOf("029A"), Task1.numericKeypad).first().length)

        val path2 = "v<<A>>^A<A>AvA<^AA>A<vAAA>^A"
        assertEquals(
            path2.length,   // 28
            Task1.keypadPaths(setOf(path1), Task1.directionalKeypad).first().length)

        val path3 = "<vA<AA>>^AvAA<^A>A<v<A>>^AvA^A<vA>^A<v<A>^A>AAvA^A<v<A>A>^AAAvA<^A>A"
        assertEquals(
            path3.length,   // 68
            Task1.keypadPaths(setOf(path2), Task1.directionalKeypad).first().length)
    }

    companion object {
        @JvmStatic
        fun paths() = listOf(
            Arguments.of("029A", "<vA<AA>>^AvAA<^A>A<v<A>>^AvA^A<vA>^A<v<A>^A>AAvA^A<v<A>A>^AAAvA<^A>A"),
            Arguments.of("980A", "<v<A>>^AAAvA^A<vA<AA>>^AvAA<^A>A<v<A>A>^AAAvA<^A>A<vA>^A<A>A"),
            Arguments.of("179A", "<v<A>>^A<vA<A>>^AAvAA<^A>A<v<A>>^AAvA^A<vA>^AA<A>A<v<A>A>^AAAvA<^A>A"),
            Arguments.of("456A", "<v<A>>^AA<vA<A>>^AAvAA<^A>A<vA>^A<A>A<vA>^A<A>A<v<A>A>^AAvA<^A>A"),
            Arguments.of("379A", "<v<A>>^AvA^A<vA<AA>>^AAvA<^A>AAvA^A<vA>^AA<A>A<v<A>A>^AAAvA<^A>A"),
        )
    }

    @ParameterizedTest
    @MethodSource("paths")
    fun `should successfully find shortest path`(code: String, path: String) {
        assertEquals(path.length, Task1.shortestPath(code).length)
    }

    @Test
    fun `should successfully pass task example`() {
        val input = """
            029A
            980A
            179A
            456A
            379A
        """.trimIndent()
        val data = input.lines()
        assertEquals(126384, Task1.solve(data))
    }

    @Test
    fun `should successfully solve the real task`() {
        val input = File("src/test/resources/day_21/TaskData.txt").readText()
        val data = input.lines()
        assertEquals(203814, Task1.solve(data))
    }
}