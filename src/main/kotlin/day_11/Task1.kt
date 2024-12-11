package day_11

class Task1 {

    companion object {

        fun solve(stones: List<Long>, blinks: Int): Int {
            var state = stones.toMutableList()
            repeat(blinks) {
                println("$it. size=${state.size}")
                state = blink(state)
            }
            return state.size
        }

        fun blink(stones: MutableList<Long>): MutableList<Long> {
            var i = 0
            while (i < stones.size) {
                if (stones[i] == 0L) {
                    stones[i] = 1
                } else {
                    var num = stones[i]
                    var count = 0
                    var mul = 1L
                    do {
                        mul *= 10
                        num /= 10
                        count++
                    } while (num > mul)
                    val left = num
                    val right = stones[i] % mul
                    while (num > 0) {
                        num /= 10
                        count++
                    }
                    if (count % 2 == 0) {
                        stones[i++] = left
                        stones.add(i, right)
                    } else {
                        stones[i] = stones[i] * 2024
                    }
                }
                i++
            }
            return stones
        }
    }
}