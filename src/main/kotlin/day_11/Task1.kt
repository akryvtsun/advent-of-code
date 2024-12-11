package day_11

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class Task1 {

    companion object {

        fun solve(stones: List<Long>, blinks: Int): Int {
            var state = stones.toMutableList()
            repeat(blinks) {
                state = blink(state)
            }
            return state.size
        }

        fun blink(stones: MutableList<Long>): MutableList<Long> {
            var i = 0
            runBlocking {
                while (i < stones.size) {
                    if (stones[i] == 0L) {
                        stones[i] = 1
                    } else {
                        val strStone = stones[i].toString()
                        if (strStone.length % 2 == 0) {
                            val first = async { strStone.substring(0..<strStone.length / 2).toLong() }
                            val second = async { strStone.substring(strStone.length / 2..<strStone.length).toLong() }
                            stones[i++] = first.await()
                            stones.add(i, second.await())
                        } else {
                            stones[i] = stones[i] * 2024
                        }
                    }
                    i++
                }
            }
            return stones
        }
    }
}