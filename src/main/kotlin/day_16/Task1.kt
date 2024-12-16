package day_16

class Task1 {

    data class Step(val pos: Point, val dir: Direction)

    companion object {

        fun evaluate(path: List<Step>): Long {
            val steps = path.size-1
            var turns = 0L
            path.zipWithNext()
                .forEach { (a, b) -> if (a.dir != b.dir) turns++ }
            return turns * 1000 + steps
        }

        fun solve(config: Labyrinth): Long {
            val pathList = mutableListOf<List<Step>>()

            val init = listOf(Step(config.start, Direction.RIGHT))
            val pq = ArrayDeque<List<Step>>()
            pq.add(init)
            while (pq.isNotEmpty()) {
                val path = pq.removeFirst()
                val curPos = path.last()
                if (curPos.pos == config.end) {
                    pathList.add(path)
                }
                else {
                    Direction.entries
                        .map { Step(curPos.pos + it.delta, it) }
                        .filter { it !in path }
                        .forEach { pq.addLast(path + it) }
                }
            }

            return pathList.minOf { evaluate(it) }
        }
    }
}