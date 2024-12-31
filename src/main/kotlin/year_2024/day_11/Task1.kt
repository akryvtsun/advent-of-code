package year_2024.day_11

class Task1 {

    companion object {

        fun solve(stones: List<Long>, blinks: Int): Int {
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
    }
}