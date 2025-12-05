package year_2025

class Day05(input: String) {

    val idRanges = input.substringBefore("\n\n").lines().map {
            val range = it.trim().split("-")
            range[0].toLong()..range[1].toLong()
        }

    val ids = input.substringAfter("\n\n").lines().map(String::toLong)

    fun solvePart1(): Int {
        return ids.count { id ->
            idRanges.any { it.contains(id) }
        }
    }

    fun solvePart2(): Long {
        tailrec fun merge(ranges: Set<LongRange>) : Set<LongRange> {
            var changed = false
            val newRanges = ranges.fold(mutableSetOf<LongRange>()) { acc, e ->
                if (acc.isEmpty()) {
                    acc.add(e)
                    acc
                } else {
                    val newAcc = mutableSetOf<LongRange>()
                    var wasMerged = false
                    for (ae in acc) {
                        if (ae.last <= e.first || e.last <= ae.first) {
                            newAcc.add(ae)
                            continue
                        }
                        newAcc.add(minOf(ae.first, e.first)..maxOf(ae.last, e.last))
                        wasMerged = true
                        changed = true
                    }
                    if (!wasMerged) newAcc.add(e)
                    newAcc
                }
            }
            return if (changed) merge(newRanges) else newRanges
        }

        println(idRanges)
        return merge(idRanges.toSet())
            .also { println(it) }
            .sumOf { it.last - it.first + 1 }
    }
}