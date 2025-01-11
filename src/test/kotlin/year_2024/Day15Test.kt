package year_2024

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import taskData
import year_2024.day_15.Command
import year_2024.day_15.Task1
import year_2024.day_15.Task2
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 15: Warehouse Woes")
class Day15Test {

    val input = """
        ##########
        #..O..O.O#
        #......O.#
        #.OO..O.O#
        #..O@..O.#
        #O#..O...#
        #O..O..O.#
        #.OO.O.OO#
        #....O...#
        ##########
        
        <vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^
        vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v
        ><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<
        <<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^
        ^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><
        ^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^
        >^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^
        <><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>
        ^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>
        v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^
    """.trimIndent()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        fun transform(input: String): Pair<Task1.Warehouse, List<Command>> {
            val blocks = input.split("\n\n")
            val wrhs = Task1.Warehouse(blocks[0])
            val cmds = blocks[1]
                .filter { it != '\n' }
                .map { c -> Command.entries.first { it.c == c } }
            return wrhs to cmds
        }

        @Test
        fun `should successfully pass small task example`() {
            val config = """
                ########
                #..O.O.#
                ##@.O..#
                #...O..#
                #.#.O..#
                #...O..#
                #......#
                ########
                
                <^^>>>vv<v>>v<<
            """.trimIndent()
            val input = transform(config)
            assertEquals(2028, Task1.solve(input.first, input.second))
        }

        @Test
        fun `should successfully pass big task example`() {
            val input = transform(input)
            assertEquals(10092, Task1.solve(input.first, input.second))
        }

        @Test
        fun `Actual answer`() {
            val map = taskData(2024, 15).readText()
            val input = transform(map)
            assertEquals(1515788, Task1.solve(input.first, input.second))
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        fun transform(input: String): Pair<Task2.Warehouse, List<Command>> {
            val blocks = input.split("\n\n")
            val wrhs = Task2.Warehouse(blocks[0])
            val cmds = blocks[1]
                .filter { it != '\n' }
                .map { c -> Command.entries.first { it.c == c } }
            return wrhs to cmds
        }

        @Test
        fun `Matches example`() {
            val data = transform(input)
            assertEquals(9021, Task2.solve(data.first, data.second))
        }

        @Test
        fun `Actual answer`() {
            val map = taskData(2024, 15).readText()
            val input = transform(map)
            assertEquals(1516544, Task2.solve(input.first, input.second))
        }
    }
}