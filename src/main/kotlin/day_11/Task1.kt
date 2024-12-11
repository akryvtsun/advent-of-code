package day_11

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.ConcurrentHashMap

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
            val toAdd = ConcurrentHashMap<Int, Long>()
            runBlocking(Dispatchers.Default) {
                for (i in stones.indices) {
                    launch {
                        if (stones[i] == 0L) {
                            stones[i] = 1
                        } else {
                            var num = stones[i]
                            var count = 0
                            var mul = 1L
                            // find middle of number
                            do {
                                mul *= 10
                                num /= 10
                                count++
                            } while (num > mul)
                            val left = num
                            val right = stones[i] % mul
                            // define number count of digits
                            while (num > 0) {
                                num /= 10
                                count++
                            }
                            if (count % 2 == 0) {
                                stones[i] = left
                                toAdd.put(i + 1, right)
                            } else {
                                stones[i] = stones[i] * 2024
                            }
                        }
                    }
                }
            }
            var shift = 0
            for (i in toAdd.keys.sorted()) {
                stones.add(i + shift, toAdd.get(i)!!)
                shift++
            }
            return stones
        }
    }
}