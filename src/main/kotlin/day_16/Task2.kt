package day_16

import java.util.PriorityQueue

class Task2 {

    data class Step(val pos: Point, val dir: Direction)

    companion object {

        fun solve(config: Labyrinth): Int {
            val initPos = Step(config.start, Direction.RIGHT)
            val unvisited = PriorityQueue<Pair<Step, Int>>(compareBy { it.second })
            unvisited.add(initPos to 0)
            val visited = mutableSetOf<Step>()
            while (unvisited.isNotEmpty()) {
                val path = unvisited.poll().also { visited += it.first }
                val curStep = path.first
                if (curStep.pos == config.end) {
                    return path.second
                } else {
                    unvisited += listOf(-1, 0, 1)
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
                }
            }
            return -1
        }
    }
}