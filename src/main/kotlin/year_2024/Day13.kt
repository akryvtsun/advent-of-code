package year_2024

import AocDay

class Day13(input: String) : AocDay<Int, Long>(input) {

    data class Point(val y: Long, val x: Long)

    data class SlotMachine(val dA: Point, val dB: Point, val prize: Point)

    fun transform(input: String, shift: Long = 0) = input.split("\n\n")
        .map { config ->
            val (a, b, p) = config.lines()
            // Button A
            val (dAx, dAy) = """Button A: X\+(\d\d), Y\+(\d\d)""".toRegex().find(a)!!.destructured
            val dA = Point(dAy.toLong(), dAx.toLong())
            // Button B
            val (dBx, dBy) = """Button B: X\+(\d\d), Y\+(\d\d)""".toRegex().find(b)!!.destructured
            val dB = Point(dBy.toLong(), dBx.toLong())
            // Prize
            val (px, py) = """Prize: X=(\d+), Y=(\d+)""".toRegex().find(p)!!.destructured
            val prize = Point(py.toLong() + shift, px.toLong() + shift)
            SlotMachine(dA, dB, prize)
        }

    fun solve(slots: List<SlotMachine>): Long {
        return slots.sumOf {
            val (a, b) = getMinCoins(it)
            3 * a + b
        }
    }

    private fun getMinCoins(slot: SlotMachine): Pair<Long, Long> {
        with(slot) {
            val b = (dA.x * prize.y - dA.y * prize.x) / (dA.x * dB.y - dA.y * dB.x)
            val a = (prize.y - dB.y * b) / dA.y
            val check = dA.y * a + dB.y * b == prize.y && dA.x * a + dB.x * b == prize.x
            return if (check) Pair(a, b) else Pair(0, 0)
        }
    }

    override fun solvePart1(): Int {
        val data = transform(input)
        return solve(data).toInt()
    }

    override fun solvePart2(): Long {
        val data = transform(input, 10000000000000)
        return solve(data)
    }
}