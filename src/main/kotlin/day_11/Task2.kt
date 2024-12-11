package day_11

class Task2 {

    companion object {

        data class Elem(var value: Long, var next: Elem?)

        data class MyList(val data: List<Long>) {

            var tail: Elem? = null

            init {
                var next: Elem? = null
                for (i in data.size-1  downTo 0) {
                    next = Elem(data[i], next)
                }
                tail = next
            }

            fun size(): Int {
                var count = 0
                var next = tail
                while (next != null) {
                    count++
                    next = next.next
                }
                return count
            }
        }

        fun solve(stones: List<Long>, blinks: Int): Int {
            var state = MyList(stones)
            repeat(blinks) {
                println("$it. size=${state.size()}")
                state = blink(state)
            }
            return state.size()
        }

        fun processElem(next: Elem) {
            val value = next.value
            if (value == 0L) {
                next.value = 1
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
                // define number count of digits
                while (num > 0) {
                    num /= 10
                    count++
                }
                if (count % 2 == 0) {
                    next.value = left
                    val n = next.next
                    val right = value % mul
                    next.next = Elem(right, n)
                } else {
                    next.value = value * 2024
                }
            }
        }

        fun blink(stones: MyList): MyList {
            var next: Elem? = stones.tail
            /*runBlocking(Dispatchers.Default) {*/
                while (next != null) {
                    val oldNext = next
                    next = next!!.next
                    /*launch {*/ processElem(oldNext!!) /*}*/
                }
            /*}*/
            return stones
        }
    }
}