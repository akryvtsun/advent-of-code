package day_23

class Task2 {

    companion object {

        private fun addPair(model: MutableMap<String, MutableSet<String>>, p: Pair<String, String>) {
            model.computeIfAbsent(p.first) { mutableSetOf() }.add(p.second)
            model.computeIfAbsent(p.second) { mutableSetOf() }.add(p.first)
        }

        fun solve(links: List<Pair<String, String>>): String {
            // build model
            val model = mutableMapOf<String, MutableSet<String>>()
            for (link in links) {
                addPair(model, link)
            }
            // find max party
            val maxParty = buildSet {
                for (key in model.keys) {
                    val party = mutableSetOf<String>()

                    fun connectWithAllPartyElems(elem: String) = party.all { elem in model[it]!! }

                    val queue = ArrayDeque<Pair<String, Set<String>>>()
                    queue.add(key to model[key]!!)
                    while (queue.isNotEmpty()) {
                        val candidate = queue.removeFirst()
                        for (host in candidate.second) {
                            val n = model[host]!!
                            val next = n.filter { it !in party && connectWithAllPartyElems(it) }
                            if (next.isNotEmpty()) {
                                party += candidate.first
                                queue.add(host to next.toSet())
                            }
                        }
                    }
                    add(party)
                }
            }
                .maxBy { it.size }
            // create party password
            return maxParty.sorted().joinToString(separator = ",")
        }
    }
}