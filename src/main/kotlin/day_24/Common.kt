package day_24

typealias State = MutableMap<String, Boolean>
typealias Schema = List<Gate>

data class Gate(var in1: String, val in2: String, val out: String, val op: (Boolean, Boolean) -> Boolean) {

    fun execute(memory: State) {
        val op1 = memory[in1]!!
        val op2 = memory[in2]!!
        memory[out] = op(op1, op2)
    }
}

fun transform(input: String): Pair<State, Schema> {
    val blocks = input.split("\n\n")
    return getInit(blocks[0]).toMutableMap() to getSchema(blocks[1])
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

private val SKELETON = """(\w+) (AND|OR|XOR) (\w+) -> (\w+)""".toRegex()

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







