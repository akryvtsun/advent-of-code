package year_2025

class Day05(input: String) {

    val ranges = input.substringBefore("\n\n").lines().map {
            val range = it.trim().split("-")
            range[0].toLong()..range[1].toLong()
        }

    val ids = input.substringAfter("\n\n").lines().map(String::toLong)

    fun solvePart1(): Int = ids.count { id -> ranges.any { id in it } }

    fun solvePart2(): Long {
        fun List<LongRange>.compact() = sequence {
            if (isEmpty()) return@sequence

            var optimized = first()
            for (current in drop(1)) {
                if (current.first <= optimized.last) {
                    optimized = optimized.first..maxOf(optimized.last, current.last)
                } else {
                    yield(optimized)
                    optimized = current
                }
            }
            yield(optimized)
        }

        fun LongRange.length(): Long = last - first + 1

        return ranges.sortedBy { it.first }
            .compact()
            .sumOf { it.length() }
    }
}