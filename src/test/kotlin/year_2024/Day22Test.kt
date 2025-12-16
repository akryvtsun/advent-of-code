package year_2024

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 22: Monkey Market")
class Day22Test {

    companion object {
        val testInput1 = """
            1
            10
            100
            2024
        """.trimIndent()

        val realInput = TaskData(2024, 22).asString()

        @JvmStatic
        fun part1Data() = listOf(
            arguments(testInput1, 37327623),
            arguments(realInput, 14119253575)
        )

        val testInput2 = """
            1
            2
            3
            2024
        """.trimIndent()

        @JvmStatic
        fun part2Data() = listOf(
            arguments(testInput2, 23),
            arguments(realInput, 1600)
        )
    }

    @Test
    fun `should successfully gen 10 secret numbers`() {
        var num = 123L
        val secrets = List(10) {
            num = Day22.nextSecret(num)
            num
        }
        assertEquals(
            listOf<Long>(
                15887950, 16495136, 527345, 704524, 1553684,
                12683156, 11100544, 12249484, 7753432, 5908254),
            secrets
        )
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: String, result: Long) {
        assertThat(
            Day22(input).solvePart1()
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: String,result: Long) {
        assertThat(
            Day22(input).solvePart2()
        ).isEqualTo(result)
    }
}