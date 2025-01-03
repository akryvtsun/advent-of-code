package year_2024.day_19

data class Node(var isTerminal: Boolean = false, var next: MutableMap<Char, Node>? = null)

fun buildPrefixTree(patterns: List<String>): Node {
    val root = Node()
    for (pattern in patterns) {
        var candidate = root
        for (c in pattern) {
            if (candidate.next == null) {
                candidate.next = mutableMapOf()
            }
            candidate = candidate.next!!.computeIfAbsent(c) { Node() }
        }
        candidate.isTerminal = true
    }
    return root
}

fun findTails(design: String, root: Node, tail: Int) : List<Int> {
    val newTails = mutableListOf<Int>()
    var i = tail
    var node = root

    while (true) {
        if (node.next == null || i == design.length) break
        val c = design[i]
        val candidate = node.next!![c]
        if (candidate != null && candidate.isTerminal) {
            newTails += i + 1
        }
        if (candidate == null) break
        node = candidate
        i++
    }

    return newTails
}





