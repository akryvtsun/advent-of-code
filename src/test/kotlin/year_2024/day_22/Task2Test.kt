package year_2024.day_22

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Task2Test {

    @Test
    fun `should successfully gen 10 secret numbers`() {
        var num = 123L
        val secrets = List(10) {
            num = Task1.nextSecret(num)
            num
        }
        assertEquals(
            listOf<Long>(
                15887950, 16495136, 527345, 704524, 1553684,
                12683156, 11100544, 12249484, 7753432, 5908254),
            secrets
        )
    }

    @Test
    fun `should successfully pass task example`() {
        val input = """
            1
            2
            3
            2024
        """.trimIndent()
        val data = transform(input)
        assertEquals(23, Task2.solve(data))
    }

    @Test
    fun `should successfully solve the real task`() {
        val input = File("src/test/resources/year_2024/day_22/TaskData.txt").readText()
        val data = transform(input)
        assertEquals(1600, Task2.solve(data))
    }
}