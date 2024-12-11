package day_11

class Task2 {

    companion object {

        fun solve(stones: List<Long>, blinks: Int): Long {
            var state = stones.groupingBy { it }.eachCount().map { it.key to it.value.toLong() }.toMap()
            repeat(blinks) {
                println("$it. size=${state.size}")
                state = blink(state)
            }
            return state.values.sumOf { it.toLong() }
        }

        fun blink(stones: Map<Long, Long>): Map<Long, Long> {
            val newState = mutableMapOf<Long, Long>()
            for (stone in stones) {
                if (stone.key == 0L) {
                    newState.add(1L,  stone.value)
                } else {
                    val str = stone.key.toString()
                    if (str.length % 2 == 0) {
                        newState.add(str.substring(0..<str.length/2).toLong(), stone.value)
                        newState.add(str.substring(str.length/2).toLong(), stone.value)
                    }
                    else {
                        newState.add(stone.key * 2024L, stone.value)
                    }
                }
            }
            return newState
        }

        fun MutableMap<Long, Long>.add(stone: Long, count: Long) {
            val oldCount = this[stone] ?: 0
            this[stone] = oldCount + count
        }
    }
}