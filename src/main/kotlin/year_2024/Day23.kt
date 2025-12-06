package year_2024

import java.util.BitSet

class Day23(input: String) {

    val links = transform(input)

    fun transform(input: String): List<Pair<String, String>> {
        return input.lines()
            .map { link ->
                val (left, right) = link.split("-")
                left to right
            }
    }

    fun MutableMap<String, MutableSet<String>>.addPair(p: Pair<String, String>) {
        computeIfAbsent(p.first) { mutableSetOf() }.add(p.second)
        computeIfAbsent(p.second) { mutableSetOf() }.add(p.first)
    }

    fun solvePart1(): Int {
        // build model
        val model = mutableMapOf<String, MutableSet<String>>()
        links.forEach { model.addPair(it) }
        // find 't'-triples
        return model.keys
            .flatMap { first ->
                val set = model[first]!!
                set
                    .flatMap { second ->
                        val record = model[second]!!
                        (set - second)
                            .filter { third -> record.contains(third) }
                            .map { third -> setOf(first, second, third) }
                    }
            }
            .toSet()
            .count { triple -> triple.any { it.startsWith('t') } }
    }

    fun solvePart2(): String {
        // build model
        val model = mutableMapOf<String, MutableSet<String>>()
        links.forEach { model.addPair(it) }

        // find max party
        var maxParty = emptySet<String>()

        model.entries.forEach {
            val set = (it.value + it.key).toList()
            (1..<(1L shl set.size)).forEach { combination ->
                val party = mutableSetOf<String>()
                val bits = BitSet.valueOf(longArrayOf(combination))
                for (i in set.indices) {
                    if (bits.get(i)) {
                        party += set[i]
                    }
                }

                fun allLinked(party: Set<String>): Boolean {

                    fun connectedWithRestOfParty(host: String, rest: Set<String>) = rest.all { host in model[it]!! }

                    return party.all { connectedWithRestOfParty(it, party - it) }
                }

                if (allLinked(party)) {
                    if (maxParty.size < party.size) {
                        maxParty = party
                    }
                }
            }
        }

        // create party password
        return maxParty.sorted().joinToString(separator = ",")
    }

}