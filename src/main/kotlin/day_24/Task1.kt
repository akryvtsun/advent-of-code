package day_24

import java.util.BitSet

class Task1 {

    companion object {

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