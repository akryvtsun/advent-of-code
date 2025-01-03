package year_2024.day_23

class Task1 {

    companion object {

        fun solve(links: List<Pair<String, String>>): Int {
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
    }
}