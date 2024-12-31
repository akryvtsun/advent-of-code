package year_2024.day_16

import java.util.PriorityQueue

class Task2 {

    companion object {

        fun solve(config: Labyrinth): Int {
            val initPos = Step(config.start, Direction.RIGHT)
            val unvisited = PriorityQueue<Pair<List<Step>, Int>>(compareBy { it.second })
            unvisited.add(listOf(initPos) to 0)
            var best: Int? = null
            val res = mutableSetOf<Point>()
            val visited = mutableSetOf<Step>()
            while (unvisited.isNotEmpty()) {
                val path = unvisited.poll().also { visited += it.first }
                val curStep = path.first.last()
                if (curStep.pos == config.end) {
                    if (best == null) {
                        best = path.second
                        res += path.first.map { it.pos }
                    } else if (path.second == best) {
                        res += path.first.map { it.pos }
                    }
                } else {
                    if (best == null || path.second < best) {
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
                            .map { (path.first + it.first) to it.second }
                    }
                }
            }
            return res.size
        }
    }
}