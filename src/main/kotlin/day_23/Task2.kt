package day_23

class Task2 {

    companion object {

        private fun addPair(model: MutableMap<String, MutableSet<String>>, p: Pair<String, String>) {
            model.computeIfAbsent(p.first) { mutableSetOf() }.add(p.second)
            model.computeIfAbsent(p.second) { mutableSetOf() }.add(p.first)
        }

        fun solve(links: List<Pair<String, String>>): String {
            val model = mutableMapOf<String, MutableSet<String>>()
            for (link in links) {
                addPair(model, link)
            }
            return ""
        }
    }
}