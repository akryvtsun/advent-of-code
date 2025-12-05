package year_2025

class Day05(input: String) {

    val idRanges = input.substringBefore("\n\n").lines()
        .map {
            val range = it.trim().split("-")
            range[0].toLong()..range[1].toLong()
        }

    val ids = input.substringAfter("\n\n").lines()
        .map(String::toLong)

    fun solvePart1(): Int {
        return ids.count { id ->
            idRanges.any { it.contains(id) }
        }
    }

    fun solvePart2(): Int {
        return idRanges.fold(mutableSetOf<Long>())
        { acc, e ->
            acc.addAll(e.iterator().asSequence().toSet())
            acc
        }.size
//
//        return idRanges
//            .sumOf { it.last - it.first + 1 }
    }
}