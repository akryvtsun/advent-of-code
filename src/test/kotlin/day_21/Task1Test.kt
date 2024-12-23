package day_21

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task1Test {

    @Test
    fun `should successfully find path for 029A`() {
        val path1 = "<A^A>^^AvvvA"
        assertEquals(
            path1.length,
            Task1.numKeypadPaths("029A").length)
        val path2 = "v<<A>>^A<A>AvA<^AA>A<vAAA>^A"
        assertEquals(
            path2.length,
            Task1.dirKeypadPaths(path1).length)
        assertEquals(
            "<vA<AA>>^AvAA<^A>A<v<A>>^AvA^A<vA>^A<v<A>^A>AAvA^A<v<A>A>^AAAvA<^A>A".length,
            Task1.dirKeypadPaths(path2).length)
    }

    @Test
    fun `should successfully find shortest paths`() {
        assertEquals(
            "<vA<AA>>^AvAA<^A>A<v<A>>^AvA^A<vA>^A<v<A>^A>AAvA^A<v<A>A>^AAAvA<^A>A".length,
            Task1.shortestPath("029A").length
        )
//        assertEquals("<v<A>>^AAAvA^A<vA<AA>>^AvAA<^A>A<v<A>A>^AAAvA<^A>A<vA>^A<A>A".length, Task1.shortestPath("980A").length)
//        assertEquals("<v<A>>^A<vA<A>>^AAvAA<^A>A<v<A>>^AAvA^A<vA>^AA<A>A<v<A>A>^AAAvA<^A>A".length, Task1.shortestPath("179A").length)
//        assertEquals("<v<A>>^AA<vA<A>>^AAvAA<^A>A<vA>^A<A>A<vA>^A<A>A<v<A>A>^AAvA<^A>A".length, Task1.shortestPath("456A").length)
//        assertEquals("<v<A>>^AvA^A<vA<AA>>^AAvA<^A>AAvA^A<vA>^AA<A>A<v<A>A>^AAAvA<^A>A".length, Task1.shortestPath("379A").length)
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
        assertEquals(-1, Task1.solve(data))
    }
}