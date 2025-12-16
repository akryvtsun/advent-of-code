package year_2024

import AocDay
import java.util.PriorityQueue

class Day19(input: String) : AocDay<Int, Long>(input) {

    fun transform(input: String): Pair<List<String>, List<String>> {
        val (patterns, designs) = input.split("\n\n")
        return patterns.split(", ") to designs.lines()
    }

    data class Node(var isTerminal: Boolean = false, var next: MutableMap<Char, Node>? = null)

    fun buildPrefixTree(patterns: List<String>): Node {
        val root = Node()
        for (pattern in patterns) {
            var candidate = root
            for (c in pattern) {
                if (candidate.next == null) {
                    candidate.next = mutableMapOf()
                }
                candidate = candidate.next!!.getOrPut(c) { Node() }
            }
            candidate.isTerminal = true
        }
        return root
    }

    fun findTails(design: String, root: Node, tail: Int): List<Int> {
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

    override fun solvePart1(): Int {
        fun isPossible(design: String, root: Node): Boolean {
            val queue = LinkedHashSet(listOf(0))
            while (true) {
                if (queue.isEmpty()) return false
                val tail = queue.removeFirst()
                if (tail == design.length) return true
                val newTails = findTails(design, root, tail)
                queue.addAll(newTails)
            }
        }

        val (patterns, designs) = transform(input)
        val root = buildPrefixTree(patterns)
        return designs.count { isPossible(it, root) }
    }

    override fun solvePart2(): Long {
        fun variants(design: String, root: Node): Long {
            val queue = PriorityQueue<Pair<Int, Long>>(compareBy { it.first })
            queue.add(0 to 1L)
            while (true) {
                if (queue.isEmpty()) return 0
                val (tIndex, tCount) = queue.remove()
                if (tIndex == design.length) return tCount
                val newTails = findTails(design, root, tIndex)
                newTails
                    .forEach { t ->
                        var pair = queue.firstOrNull { it.first == t }
                        if (pair != null) {
                            queue.remove(pair)
                        } else {
                            pair = t to 0L
                        }
                        queue.add(t to pair.second + tCount)
                    }
            }
        }

        val (patterns, designs) = transform(input)
        val root = buildPrefixTree(patterns)
        return designs.sumOf { variants(it, root) }
    }
}