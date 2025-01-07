package year_2024.day_12

class Task2 {

    data class Line(val begin: Point, val end: Point)

    companion object {

        fun perimeter(r: Region): Long {
            val points = r.genBorders()
            return compact(points).size.toLong()
        }

        private fun Region.genBorders(): List<Pair<Line, Direction>> =
            buildList {
                for (f in fields) {
                    for (d in Direction.entries) {
                        val next = f.move(d)
                        if (next !in this@genBorders) {
                            add(Line(f, f) to d)
                        }
                    }
                }
            }

        private fun compact(borders: List<Pair<Line, Direction>>): List<Pair<Line, Direction>> {
            val result = borders.toMutableList()
            var wasCompacted: Boolean
            do {
                wasCompacted = false
                outer@ for (i in 0..<result.size - 1) {
                    val (p1, d1) = result[i]
                    for (j in i + 1 until result.size) {
                        val (p2, d2) = result[j]
                        if (d1 == d2) {
                            when (d1) {
                                Direction.LEFT, Direction.RIGHT ->
                                    if (p1.end + Point(1, 0) == p2.begin) {
                                        result[i] = Line(p1.begin, p2.end) to d1
                                        result.removeAt(j)
                                        wasCompacted = true
                                        break@outer
                                    } else if (p2.end + Point(1, 0) == p1.begin) {
                                        result[j] = Line(p2.begin, p1.end) to d2
                                        result.removeAt(i)
                                        wasCompacted = true
                                        break@outer
                                    }
                                Direction.UP, Direction.DOWN ->
                                    if (p1.end + Point(0, 1) == p2.begin) {
                                        result[i] = Line(p1.begin, p2.end) to d1
                                        result.removeAt(j)
                                        wasCompacted = true
                                        break@outer
                                    } else if (p2.end + Point(0, 1) == p1.begin) {
                                        result[j] = Line(p2.begin, p1.end) to d2
                                        result.removeAt(i)
                                        wasCompacted = true
                                        break@outer
                                    }
                            }
                        }
                    }
                }
            } while (wasCompacted)
            return result
        }

        fun solve(map: List<List<Char>>): Long {
            return findRegions(map).sumOf { it.area() * perimeter(it) }
        }
    }
}