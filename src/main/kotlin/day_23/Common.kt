package day_23

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






