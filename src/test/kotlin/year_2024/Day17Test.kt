package year_2024

import TaskData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@DisplayName("Day 17: Chronospatial Computer")
class Day17Test {
    companion object {
        val testInput1 = """
            Register A: 729
            Register B: 0
            Register C: 0
            
            Program: 0,1,5,4,3,0
        """.trimIndent()

        val testInput2 = """
            Register A: 117440
            Register B: 0
            Register C: 0
            
            Program: 0,3,5,4,3,0
        """.trimIndent()

        val caseInput2 = """
            Register A: 10
            Register B: 0
            Register C: 0
            
            Program: 5,0,5,1,5,4
        """.trimIndent()

        val caseInput3 = """
            Register A: 2024
            Register B: 0
            Register C: 0
            
            Program: 0,1,5,4,3,0
        """.trimIndent()

        val realInput = TaskData(2024, 17).asString()

        @JvmStatic
        fun part1Data() = listOf(
            arguments(testInput1, "4,6,3,5,6,3,5,2,1,0"),
            arguments(testInput2, "0,3,5,4,3,0"),
            arguments(caseInput2, "0,1,2"),
            arguments(caseInput3, "4,2,5,6,7,7,7,7,3,1,0"),
            arguments(realInput, "7,5,4,3,4,5,3,4,6")
        )

        val testInput_2 = """
            Register A: -1
            Register B: -1
            Register C: -1
            
            Program: 0,3,5,4,3,0
        """.trimIndent()

        @JvmStatic
        fun part2Data() = listOf(
            arguments(testInput_2, 117440),
            arguments(realInput, 164278899142333)
        )
    }

    @ParameterizedTest
    @MethodSource("part1Data")
    fun part1Test(input: String, result: String) {
        assertThat(
            Day17(input).solvePart1()
        ).isEqualTo(result)
    }

    @ParameterizedTest
    @MethodSource("part2Data")
    fun part2Test(input: String, result: Long) {
        assertThat(
            Day17(input).solvePart2()
        ).isEqualTo(result)
    }
}