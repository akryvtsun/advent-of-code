package day_16

class Task1 {

    data class Step(val pos: Point, val dir: Direction)

    companion object {

        fun evaluate(path: List<Step>): Long {
            val steps = path.size - 1
            var turns = 0L
            path.zipWithNext()
                .forEach { (a, b) -> if (a.dir != b.dir) turns++ }
            return turns * 1000 + steps
        }

        fun solve(config: Labyrinth): Long {
            val init = listOf(Step(config.start, Direction.RIGHT))
            val pq = ArrayDeque<List<Step>>()
            pq.add(init)
            return sequence {
                while (pq.isNotEmpty()) {
                    val path = pq.removeFirst()
                    val curPos = path.last()
                    if (curPos.pos == config.end) {
                        yield(path)
                    } else {
                        val steps = path.map { it.pos }.toSet()
                        Direction.entries
                            .map { Step(curPos.pos + it.delta, it) }
                            .filter { it.pos !in config.obstacles }                 // check obstacles bump
                            .filter { it.pos !in steps }                            // check for path loops
                            .forEach { pq.addLast(path + it) }
                    }
                }
            }
                .minOf { evaluate(it) }
        }
    }
}