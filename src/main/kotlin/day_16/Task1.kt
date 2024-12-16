package day_16

class Task1 {

    data class Path(val steps: Long, val turns: Long) {
        fun evaluate(): Long  = turns * 1000 + steps
    }

    companion object {

        fun solve(config: Labyrinth): Long {
            val s: Sequence<Path> = sequenceOf(Path(0, 0))
            return s
                .minOf { it.evaluate() }
        }
    }
}