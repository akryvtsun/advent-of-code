package year_2024.day_13

data class Point(val y: Long, val x: Long)

data class SlotMachine(val dA: Point, val dB: Point, val prize: Point)

fun transform(input: String, shift: Long = 0) = input.split("\n\n")
    . map { config ->
        val (a, b, p) = config.lines()
        // Button A
        val (dAx, dAy) = """Button A: X\+(\d\d), Y\+(\d\d)""".toRegex().find(a)!!.destructured
        val dA = Point(dAy.toLong(), dAx.toLong())
        // Button B
        val (dBx, dBy) = """Button B: X\+(\d\d), Y\+(\d\d)""".toRegex().find(b)!!.destructured
        val dB = Point(dBy.toLong(), dBx.toLong())
        // Prize
        val (px, py) = """Prize: X=(\d+), Y=(\d+)""".toRegex().find(p)!!.destructured
        val prize = Point(py.toLong() + shift, px.toLong() + shift)
        SlotMachine(dA, dB, prize)
    }


