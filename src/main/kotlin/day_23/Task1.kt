package day_23

class Task1 {

    companion object {

        private fun addPair(model: MutableMap<String, MutableSet<String>>, p: Pair<String, String>) {
            model.computeIfAbsent(p.first) { mutableSetOf() }.add(p.second)
            model.computeIfAbsent(p.second) { mutableSetOf() }.add(p.first)
        }

        fun solve(links: List<Pair<String, String>>): Int {
            val model = mutableMapOf<String, MutableSet<String>>()
            for (link in links) {
                addPair(model, link)
            }
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
    }
}