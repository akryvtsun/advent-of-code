package day_13

data class Point(val y: Int, val x: Int)

data class SlotMachine(val dA: Point, val dB: Point, val prize: Point)

fun transform(input: String) = input.split("\n\n")
    . map { config ->
        val (a, b, p) = config.lines()
        // Button A
        val (dAx, dAy) = """Button A: X\+(\d\d), Y\+(\d\d)""".toRegex().find(a)!!.destructured
        val dA = Point(dAy.toInt(), dAx.toInt())
        // Button B
        val (dBx, dBy) = """Button B: X\+(\d\d), Y\+(\d\d)""".toRegex().find(b)!!.destructured
        val dB = Point(dBy.toInt(), dBx.toInt())
        // Prize
        val (px, py) = """Prize: X=(\d+), Y=(\d+)""".toRegex().find(p)!!.destructured
        val prize = Point(py.toInt(), px.toInt())
        SlotMachine(dA, dB, prize)
    }


