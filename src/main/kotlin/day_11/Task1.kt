package day_11

class Task1 {

    companion object {

        fun solve(stones: List<Long>, blinks: Int): Int {
            var state = stones
            repeat(blinks) {
                state = blink(state)
            }
            return state.size
        }

        fun blink(stones: List<Long>): List<Long> {
            val newStones = mutableListOf<Long>()
            for (stone in stones) {
                if (stone == 0L) {
                    newStones.add(1)
                } else {
                    val strStone = stone.toString()
                    if (strStone.length % 2 == 0) {
                        newStones.add(strStone.substring(0..<strStone.length/2).toLong())
                        newStones.add(strStone.substring(strStone.length/2..<strStone.length).toLong())
                    } else {
                        newStones.add(stone * 2024)
                    }
                }
            }
            return newStones
        }
    }
}