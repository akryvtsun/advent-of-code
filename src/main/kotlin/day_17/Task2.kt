package day_17

class Task2 {

    companion object {

        fun solve(config: Computer): Pair<Computer, String> {
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

            return Computer(A, B, C, config.program) to output.joinToString(separator = ",")
        }
    }
}