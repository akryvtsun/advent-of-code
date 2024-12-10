package day_10

class Task1 {

    companion object {

        fun solve(input: IslandMap): Int {
            return sequence {
                for (y in 0 until input.height()) {
                    for (x in 0 until input.width()) {
                        yield(Point(y, x))
                    }
                }
            }
                .filter { input.isStart(it) }
                .sumOf { input.getScore(it) }
        }

        fun IslandMap.getScore(p: Point): Int {
            val riched = mutableSetOf<Point>()

            fun getScoreImpl(p: Point) {
                if (isEnd(p)) {
                    riched.add(p)
                }
                else {
                    val newPs = Direction.entries
                        .map { p + it }
                        .filter { isInMap(it) }
                        .filter { isRouteStep(p, it) }
                    newPs.forEach { getScoreImpl(it) }
                }
            }

            getScoreImpl(p)
            return riched.size
        }
    }
}