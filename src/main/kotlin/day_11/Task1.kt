package day_11

import java.util.LinkedList

class Task1 {

    companion object {

        fun solve(stones: List<Long>, blinks: Int): Int {
            var state = LinkedList(stones)
            repeat(blinks) {
                println("$it. size=${state.size}")
                state = blink(state)
            }
            return state.size
        }

        fun blink(stones: LinkedList<Long>): LinkedList<Long> {
            val iter = stones.listIterator()
            while (iter.hasNext()) {
                val value = iter.next()
                if (value == 0L) {
                    iter.set(1)
                } else {
                    var num = value
                    var count = 0
                    var mul = 1L
                    // find middle of number
                    do {
                        mul *= 10
                        num /= 10
                        count++
                    } while (num > mul)
                    val left = num
                    val right = value % mul
                    // define number count of digits
                    while (num > 0) {
                        num /= 10
                        count++
                    }
                    if (count % 2 == 0) {
                        iter.set(left)
                        iter.add(right)
                    } else {
                        iter.set(value * 2024)
                    }
                }
            }
            return stones
        }
    }
}