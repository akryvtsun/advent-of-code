package year_2024.day_11

class Task2 {

    companion object {

        fun solve(stones: List<Long>, blinks: Int): Long {
            var state = stones.groupingBy { it }.eachCount()
                .map { it.key to it.value.toLong() }
                .toMap()
            repeat(blinks) {
                state = blink(state)
            }
            return state.values.sumOf { it }
        }

        fun blink(stones: Map<Long, Long>): Map<Long, Long> {
            return buildNonMaterializedList {
                for (stone in stones) {
                    if (stone.key == 0L) {
                        add(1L,  stone.value)
                    } else {
                        val str = stone.key.toString()
                        if (str.length % 2 == 0) {
                            add(str.substring(0..<str.length/2).toLong(), stone.value)
                            add(str.substring(str.length/2).toLong(), stone.value)
                        }
                        else {
                            add(stone.key * 2024L, stone.value)
                        }
                    }
                }
            }
        }

        fun buildNonMaterializedList(builder: MutableMap<Long, Long>.() -> Unit): MutableMap<Long, Long> {
            val map = mutableMapOf<Long, Long>()
            map.builder()
            return map
        }

        fun MutableMap<Long, Long>.add(stone: Long, count: Long) {
            val oldCount = this[stone] ?: 0
            this[stone] = oldCount + count
        }
    }
}