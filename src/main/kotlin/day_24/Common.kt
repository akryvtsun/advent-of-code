package day_24

typealias State = Map<String, Boolean>
typealias Schema = List<Element>

data class Element(var in1: String, val in2: String, val out: String, val op: (Boolean, Boolean) -> Boolean)

fun transform(input: String): Pair<State, Schema> {
    val blocks = input.split("\n\n")
    return getInit(blocks[0]) to getSchema(blocks[1])
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
                "AND" -> Element(in1, in2, out) { op1, op2 -> op1 and op2 }
                "OR" -> Element(in1, in2, out) { op1, op2 -> op1 or op2 }
                "XOR" -> Element(in1, in2, out) { op1, op2 -> op1 xor op2 }
                else -> error("unknown operation")
            }
        }







