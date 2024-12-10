package day_10

class Task2 {

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
                .sumOf { input.getRate(it) }
        }

        fun IslandMap.getRate(p: Point): Int {
            val routs = mutableSetOf<List<Point>>()

            fun getRateImpl(p: Point, route: List<Point>) {
                val newRoute = route + p
                if (isEnd(p)) {
                    routs += newRoute
                }
                else {
                    val newPs = Direction.entries
                        .map { p + it }
                        .filter { isInMap(it) }
                        .filter { isRouteStep(p, it) }
                    newPs.forEach { getRateImpl(it, newRoute) }
                }
            }

            getRateImpl(p, emptyList())
            return routs.size
        }

    }
}