package day_24

class Task2 {

    data class MatOp(val cmd: String, val operands: Set<String>)

    companion object {

        private fun getSchema(input: String) =
            input.lines()
                .map {
                    val (in1, oper, in2, out) = SKELETON.find(it)!!.destructured
                    MatOp(oper, setOf(in1, in2)) to out
                }
                .toMap()

        fun transform(input: String): Map<MatOp, String> {
            val blocks = input.split("\n\n")
            return getSchema(blocks[1])
        }

        fun solve(gates: Map<MatOp, String>): String {
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
}