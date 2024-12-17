package day_17

class Task1 {

    companion object {

        fun solve(config: Computer): String {
            val output = mutableListOf<Int>()

            var A = config.A
            var B = config.B
            var C = config.C
            var i = 0

            fun getValue(operand: Int): Int =
                when (operand) {
                    in 0..3 -> operand
                    4 -> A
                    5 -> B
                    6 -> C
                    else -> throw IllegalArgumentException("Invalid operation")
                }

            while (i < config.program.size) {
                val opcode = config.program[i++]
                val operand = config.program[i++]
                val value = getValue(operand)
                when (opcode) {
                    0 -> { // adv
                        A /= (2 shl value)
                    }
                    1 -> { // bxl
                        B = B xor value
                    }
                    2 -> { // bst
                        B = value and 0b111
                    }
                    3 -> { // jnz
                        if (A != 0) {
                            i = value
                        }
                    }
                    4 -> { // bxc
                        B = B xor C
                    }
                    5 -> { // out
                        output += value and 0b111
                    }
                    6 -> { // bdv
                        B = A / (2 shl value)
                    }
                    7 -> { // cdv
                        C = A / (2 shl value)
                    }
                    else -> throw IllegalArgumentException("Invalid operation")
                }
            }

            return output.joinToString(separator = ",")
        }
    }
}