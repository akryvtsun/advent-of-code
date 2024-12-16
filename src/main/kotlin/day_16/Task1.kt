package day_16

import java.util.PriorityQueue

class Task1 {

    data class Step(val pos: Point, val dir: Direction)

    companion object {

        fun solve(config: Labyrinth): Int {
            val init = Step(config.start, Direction.RIGHT)
            val pq = PriorityQueue<Pair<Step, Int>>(compareBy { it.second })
            pq.add(init to 0)
            val visited = mutableSetOf<Step>()
            while (pq.isNotEmpty()) {
                val path = pq.poll().also { visited += it.first }
                val curStep = path.first
                if (curStep.pos == config.end) {
                    return path.second
                } else {
                    listOf(-1, 0, 1)
                        .map {
                            var newPos = curStep.pos
                            val newDir =
                                Direction.entries[((curStep.dir.ordinal + it) + Direction.entries.size) % Direction.entries.size]
                            var newCount = path.second
                            if (it == 0) {
                                newPos += newDir.delta
                                newCount += 1
                            } else {
                                newCount += 1000
                            }
                            val newStep = Step(newPos, newDir)
                            newStep to newCount
                        }
                        .filter { it.first.pos !in config.obstacles }
                        .filter { it.first !in visited }
                        .forEach { pq.add(it) }
                }
            }
            return -1
        }
    }
}