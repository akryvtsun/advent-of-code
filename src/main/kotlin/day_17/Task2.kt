package day_17

class Task2 {

    companion object {

        fun solve(program: List<Int>): Long {
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
                val result = pq.removeFirst()
                val potentialA = result.first
                if (result.second == program) return potentialA

                val left = potentialA * 8
                val right = (potentialA+1) * 8 - 1
                for (num in left..right) {
                    A = num
                    out = result.second.toMutableList()
                    program.windowed(2,2).forEach { execute(it[0], it[1]) }
                    if (num != 0L && out == program.subList(program.size - out.size, program.size)) {
                        pq.addLast(num to out)
                    }
                }
            }
        }
    }
}