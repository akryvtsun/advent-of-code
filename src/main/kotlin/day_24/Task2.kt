package day_24

class Task2 {

    data class MatOp(val oper: String, val operands: Set<String>)

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
            return ""
        }
    }
}