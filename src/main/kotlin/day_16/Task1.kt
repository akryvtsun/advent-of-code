package day_16

class Task1 {

    data class Step(val pos: Point, val dir: Direction)

    companion object {

        fun evaluate(path: List<Step>): Long {
            var steps = 0L
            var turns = 0L
            path.zipWithNext()
                .forEach { (a, b) -> if (a.dir != b.dir) turns++; steps++ }
            return turns * 1000 + steps
        }

        fun solve(config: Labyrinth): Long {
            val pathList = mutableListOf<List<Step>>()

            fun explore(path: List<Step>) {
                val curPos = path.last()
                if (curPos.pos == config.end) {
                    pathList.add(path)
                }
                else {
                    Direction.entries
                        .map { Step(curPos.pos + it.delta, it) }
                        .filter { it !in path }
                        .forEach { explore(path + it) }
                }
            }
            explore(listOf(Step(config.start, Direction.RIGHT)))

            return pathList.minOf { evaluate(it) }
        }
    }
}