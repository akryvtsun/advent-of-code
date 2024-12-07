package day_07

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Task1Test {

    @Test
    fun `should successfully pass task example`() {
        val map =  """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
        """.trimIndent()
        val data = map.split("\n")
            .map { line ->
                val test = line.substringBefore(':').toLong()
                val nums = line.substringAfter(": ").split(" ").map { it.toLong() }
                Task1.Equation(test, nums)
            }
        assertEquals(3749, Task1.solve(data))
    }

//    @Test
//    fun `should successfully solve the real task`() {
//        val map = File("src/test/resources/day_07/TaskData.txt").readLines().map { StringBuilder(it) }
//        println("Task solution: ${Task1.solve(map)}")   // 4433
//    }
}