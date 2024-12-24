package day_24

import java.util.BitSet

class Task1 {

    companion object {

        fun solve(init: State, links: Schema): Long {
            links.forEach { it.execute(init) }
            val res = BitSet()
            for (num in 0..99) {
                val register = String.format("z%02d", num)
                init[register]?.let { res.set(num, it) }
            }
            return res.toLongArray()[0]
        }
    }
}