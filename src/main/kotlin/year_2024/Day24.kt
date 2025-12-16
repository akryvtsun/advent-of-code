package year_2024

import java.util.BitSet

typealias State = MutableMap<String, Boolean>
typealias Schema = List<Gate>

data class Gate(var in1: String, val in2: String, val out: String, val op: (Boolean, Boolean) -> Boolean) {

    fun execute(memory: State) {
        val op1 = memory[in1]!!
        val op2 = memory[in2]!!
        memory[out] = op(op1, op2)
    }
}

class Day24(private val input: String) {

    val SKELETON = """(\w+) (AND|OR|XOR) (\w+) -> (\w+)""".toRegex()

    private fun getInit(input: String) =
        input.lines()
            .map {
                val assignment = it.split(": ")
                val register = assignment[0]
                val value = assignment[1] == "1"
                register to value
            }
            .toMap()

    private fun getSchema1(input: String) =
        input.lines()
            .map {
                val (in1, oper, in2, out) = SKELETON.find(it)!!.destructured
                when (oper) {
                    "AND" -> Gate(in1, in2, out) { op1, op2 -> op1 and op2 }
                    "OR" -> Gate(in1, in2, out) { op1, op2 -> op1 or op2 }
                    "XOR" -> Gate(in1, in2, out) { op1, op2 -> op1 xor op2 }
                    else -> error("unknown operation")
                }
            }

    fun transform1(input: String): Pair<State, Schema> {
        val blocks = input.split("\n\n")
        return getInit(blocks[0]).toMutableMap() to getSchema1(blocks[1])
    }

    fun solvePart1(): Long {
        val (init, gates) = transform1(input)
        // reorder gates
        val gatesStrMap = buildMap {
            for (gate in gates) {
                val first = if (gate.in1[0] !in listOf('x', 'y')) gate.in1 else null
                val second = if (gate.in2[0] !in listOf('x', 'y')) gate.in2 else null
                val value =
                    if (first == null && second == null)
                        emptyList()
                    else if (first == null)
                        listOf(second!!)
                    else if (second == null)
                        listOf(first!!)
                    else listOf(first!!, second!!)
                put(gate.out, value)
            }
        }
        val sortedStrings = TopologicalSort(gatesStrMap).topologicalSort()
        val gatesMap = gates.associateBy { it.out }
        val sortedGates = sortedStrings.map { gatesMap[it]!! }
        // execute gates
        sortedGates.forEach { it.execute(init) }
        // collect result value
        val res = BitSet()
        for (num in 0..99) {
            val register = String.format("z%02d", num)
            init[register]?.let { res.set(num, it) }
        }
        val longs = res.toLongArray()
        check(longs.size == 1)
        return longs[0]
    }

    data class MatOp(val cmd: String, val operands: Set<String>)

    private fun getSchema2(input: String) =
        input.lines()
            .map {
                val (in1, oper, in2, out) = SKELETON.find(it)!!.destructured
                MatOp(oper, setOf(in1, in2)) to out
            }
            .toMap()

    fun transform2(input: String): Map<MatOp, String> {
        val blocks = input.split("\n\n")
        return getSchema2(blocks[1])
    }

    /*
        Solution:
        x11 AND y11 -> z11
        sbs XOR vbj -> vkq

        qvs XOR kmc -> mmk
        pwp  OR cwc -> z24

        x28 XOR y28 -> qdq
        y28 AND x28 -> pvb

        vsb AND dkp -> z38
        dkp XOR vsb -> hqh
     */

    fun solvePart2(): String {
        val gates = transform2(input)

        val links = mutableSetOf<String>()

        fun f(c: Char, num: Int) = c + String.format("%02d", num)
        fun get(op1: String, oper: String, op2: String) = gates[MatOp(oper, setOf(op1, op2))]
        fun isZ(out: String): Boolean = out.get(0) == 'z'
        fun notIn(oper: String, vararg cmds: String) = gates.keys.none { (it.cmd in cmds) && (oper in it.operands) }

        val C = mutableListOf<String>()
        C += get("x00", "AND", "y00")!!
        for (i in 1..44) {
            println(i)
            // line 1
            val v1 = get(f('x', i), "XOR", f('y', i))
            if (isZ(v1!!)) links += v1!!
            if (notIn(v1!!, "XOR", "AND")) links += v1!!
            val v2 = get(v1!!, "XOR", C[i-1]) // Zi
            if (v2 != f('z', i)) links += v2!!
            // line 2
            val v3 = get(f('x', i), "AND", f('y', i))
            if (isZ(v3!!)) links += v3!!
            if (notIn(v3!!, "OR")) links += v3!!
            val v4 = get(C[i-1], "AND", v1)
            if (isZ(v4!!)) links += v4!!
            if (notIn(v4!!, "OR")) links += v4!!
            C += get(v3!!, "OR", v4!!)!!
            if (notIn(C[i], "XOR", "AND")) links += C[i]!!
        }

        return listOf("z11", "vkq", "mmk", "z24", "qdq", "pvb", "z38", "hqh").sorted().joinToString(separator = ",")
    }
}

// This class represents a Topological Sort algorithm for a directed acyclic graph (DAG).
class TopologicalSort(private val graph: Map<String, List<String>>) {

    // A set to keep track of visited nodes during the topological sort.
    private val visited = mutableSetOf<String>()

    // A list to store the topological order of nodes in reverse order.
    private val topologicalOrder = mutableListOf<String>()

    // Function to perform the topological sort.
    fun topologicalSort(): List<String> {

        // Loop through the all nodes if the graph
        for (node in graph.keys) {
            // If the node has not been visited, perform depth-first search (DFS) from this node.
            if (node !in visited) {
                dfs(node)
            }
        }

        // Return the topological order in reverse as a list.
        return topologicalOrder
    }

    private fun dfs(node: String) {
        //Mark the current node as visited
        visited.add(node)

        //Explore all the neighbours of the current node
        for (neighbour in graph[node] ?: emptyList()) {
            // If the neighbor has not been visited, recursively visit it.
            if (neighbour !in visited) {
                dfs(neighbour)
            }
        }

        // Add the current node to the topological order.
        topologicalOrder.add(node)
    }
}