package day_13

class Task2 {

    companion object {

        fun solve(slots: List<SlotMachine>): Int {
            return slots.sumOf {
                val (a, b) = getMinCoins(it)
                3*a + b
            }
        }

        private fun getMinCoins(slot: SlotMachine): Pair<Int, Int> {
            with(slot) {
                val b = (dA.x * prize.y - dA.y * prize.x) / (dA.x * dB.y - dA.y * dB.x)
                val a = (prize.y - dB.y * b) / dA.y
                val check = dA.y * a + dB.y * b == prize.y && dA.x * a + dB.x * b == prize.x
                return if (check) Pair(a, b) else Pair(0, 0)
            }
        }
    }
}