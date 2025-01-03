package year_2024.day_23

fun MutableMap<String, MutableSet<String>>.addPair(p: Pair<String, String>) {
    computeIfAbsent(p.first) { mutableSetOf() }.add(p.second)
    computeIfAbsent(p.second) { mutableSetOf() }.add(p.first)
}






