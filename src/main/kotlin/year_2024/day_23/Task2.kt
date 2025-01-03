package year_2024.day_23

import java.util.BitSet

class Task2 {

    companion object {

        fun solve(links: List<Pair<String, String>>): String {
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
}