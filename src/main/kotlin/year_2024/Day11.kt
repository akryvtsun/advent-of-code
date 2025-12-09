package year_2024

class Day11(input: String) {

    val stones = input.split(" ").map { it.toLong() }

    fun solvePart1(blinks: Int): Int {
        var state = stones
        repeat(blinks) {
            state = blink(state)
        }
        return state.size
    }

    fun blink(stones: List<Long>) = buildList {
        for (stone in stones) {
            if (stone == 0L) {
                add(1)
            } else {
                val strStone = stone.toString()
                if (strStone.length % 2 == 0) {
                    add(strStone.substring(0..<strStone.length/2).toLong())
                    add(strStone.substring(strStone.length/2).toLong())
                } else {
                    add(stone * 2024)
                }
            }
        }
    }

    fun solvePart2(blinks: Int): Long {
        var state = stones.groupingBy { it }.eachCount()
            .map { it.key to it.value.toLong() }
            .toMap()
        repeat(blinks) {
            state = blink2(state)
        }
        return state.values.sumOf { it }
    }

    fun blink2(stones: Map<Long, Long>): Map<Long, Long> {
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