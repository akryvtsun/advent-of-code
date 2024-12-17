package day_17

data class Computer(val A: Long, val B: Long, val C: Long, val program: List<Byte>)

fun transform(input: String): Computer {
    val blocks = input.split("\n\n")
    val regs = blocks[0].lines()
    val A =  register(regs[0])
    val B =  register(regs[1])
    val C =  register(regs[2])
    val program = blocks[1].substringAfterLast(" ").split(",").map { it.toByte() }
    return Computer(A, B, C, program)
}

fun register(data: String) = data.substringAfterLast(" ").toLong()






