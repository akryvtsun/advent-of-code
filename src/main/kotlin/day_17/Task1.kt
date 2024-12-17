package day_17

class Task1 {

    companion object {

        fun solve(config: Computer): String {
            var output = mutableListOf<Int>()

            var A = config.A
            var B = config.B
            var C = config.C
            var i = 0

            fun getOperand(oper: Int): Int =
                when (oper) {
                    in 0..3 -> oper
                    4 -> A
                    5 -> B
                    6 -> C
                    else -> throw IllegalArgumentException("Invalid operation")
                }

            while (i < config.program.size) {
                val opcode = config.program[i++]
                val operand = getOperand(config.program[i++])
                when (opcode) {
                    0 -> { // adv
                        A /= (2 shl operand)
                    }
                    1 -> { // bxl
                        B = B xor operand
                    }
                    2 -> { // bst
                        B = operand and 0b111
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
                        output += operand and 0b111
                    }
                    6 -> { // bdv
                        B = A / (2 shl operand)
                    }
                    7 -> { // cdv
                        C = A / (2 shl operand)
                    }
                    else -> throw IllegalArgumentException("Invalid operation")
                }
            }

            return output.joinToString(separator = ",")
        }
    }
}