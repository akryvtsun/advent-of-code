package day_24

import day_24.Task1.Companion.Gate
import java.util.BitSet

typealias State = MutableMap<String, Boolean>
typealias Schema = List<Gate>

class Task1 {

    companion object {

        data class Gate(var in1: String, val in2: String, val out: String, val op: (Boolean, Boolean) -> Boolean) {

            fun execute(memory: State) {
                val op1 = memory[in1]!!
                val op2 = memory[in2]!!
                memory[out] = op(op1, op2)
            }
        }

        private fun getInit(input: String) =
            input.lines()
                .map {
                    val assignment = it.split(": ")
                    val register = assignment[0]
                    val value = assignment[1] == "1"
                    register to value
                }
                .toMap()

        private fun getSchema(input: String) =
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

        fun transform(input: String): Pair<State, Schema> {
            val blocks = input.split("\n\n")
            return getInit(blocks[0]).toMutableMap() to getSchema(blocks[1])
        }

        fun solve(init: State, gates: Schema): Long {
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
    }
}