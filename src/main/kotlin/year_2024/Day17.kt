package year_2024

import AocDay

class Day17(input: String) : AocDay<String, Long>(input) {

    data class Computer(val A: Int, val B: Int, val C: Int, val program: List<Int>)

    fun transform(input: String): Computer {
        val blocks = input.split("\n\n")
        val regs = blocks[0].lines()
        val A = register(regs[0])
        val B = register(regs[1])
        val C = register(regs[2])
        val program = blocks[1].substringAfterLast(" ").split(",").map { it.toInt() }
        return Computer(A, B, C, program)
    }

    private fun register(data: String) = data.substringAfterLast(" ").toInt()

    val config = transform(input)

    override fun solvePart1(): String {
        val output = mutableListOf<Int>()

        var A = config.A
        var B = config.B
        var C = config.C
        var i = 0

        fun getCombo(operand: Int): Int =
            when (operand) {
                in 0..3 -> operand
                4 -> A
                5 -> B
                6 -> C
                else -> throw IllegalArgumentException("Invalid operand")
            }

        while (i < config.program.size) {
            val opcode = config.program[i++]
            val operand = config.program[i++]
            when (opcode) {
                0 -> { // adv
                    A /= (1 shl getCombo(operand))
                }

                1 -> { // bxl
                    B = B xor operand
                }

                2 -> { // bst
                    B = getCombo(operand) and 0b111
                }

                3 -> { // jnz
                    if (A != 0) {
                        i = operand
                    }
                }

                4 -> { // bxc
                    B = B xor C
                }

                5 -> { // out
                    output += getCombo(operand) and 0b111
                }

                6 -> { // bdv
                    B = A / (1 shl getCombo(operand))
                }

                7 -> { // cdv
                    C = A / (1 shl getCombo(operand))
                }

                else -> throw IllegalArgumentException("Invalid operation")
            }
        }

        return output.joinToString(separator = ",")
    }

    override fun solvePart2(): Long {
        val program = config.program

        var A = 0L
        var B = 0L
        var C = 0L
        var out = mutableListOf<Int>()

        fun getCombo(operand: Int): Long =
            when (operand) {
                in 0..3 -> operand.toLong()
                4 -> A
                5 -> B
                6 -> C
                else -> throw IllegalArgumentException("Invalid operand")
            }

        fun execute(opcode: Int, operand: Int) {
            when (opcode) {
                0 -> { // adv
                    A /= (1 shl getCombo(operand).toInt())
                }

                1 -> { // bxl
                    B = B xor operand.toLong()
                }

                2 -> { // bst
                    B = getCombo(operand) and 0b111
                }

                3 -> { // jnz
                    // IGNORE
                }

                4 -> { // bxc
                    B = B xor C
                }

                5 -> { // out
                    out.addFirst((getCombo(operand) and 0b111).toInt())
                }

                6 -> { // bdv
                    B = A / (1 shl getCombo(operand).toInt())
                }

                7 -> { // cdv
                    C = A / (1 shl getCombo(operand).toInt())
                }

                else -> throw IllegalArgumentException("Invalid operation")
            }
        }

        val pq = ArrayDeque<Pair<Long, List<Int>>>()
        pq.add(Pair(0, emptyList()))

        while (true) {
            val (potentialA, output) = pq.removeFirst()
            if (output == program) return potentialA

            val left = potentialA * 8
            val right = (potentialA + 1) * 8 - 1
            for (num in left..right) {
                A = num
                out = output.toMutableList()
                program.windowed(2, 2).forEach { execute(it[0], it[1]) }
                if (num != 0L && out == program.subList(program.size - out.size, program.size)) {
                    pq.addLast(num to out)
                }
            }
        }
    }
}