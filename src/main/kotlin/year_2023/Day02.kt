package year_2023

class Day02(private val input: List<String>) {

    fun solvePart1() =
        input
            .sumOf { game ->
                if (game.turns().all { it.isPossible() }) game.number() else 0
            }

    fun solvePart2() =
        input
            .sumOf { game ->
                val minSetup = mutableMapOf(
                    "red" to 0,
                    "green" to 0,
                    "blue" to 0,
                )
                game.turns().forEach {
                    it.turnOp { count, color -> minSetup[color] = maxOf(minSetup[color]!!, count) }
                }
                minSetup.values.reduce { acc, count -> acc * count }
            }

    private fun String.number() = substringBefore(":").substringAfter(" ").toInt()

    private fun String.turns() = substringAfter(":").split(";")

    val setup = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14,
    )

    private fun String.isPossible(): Boolean {
        var flag = true
        turnOp { count, color -> if (setup[color]!! < count) flag = false }
        return flag
    }

    private fun String.turnOp(operation: (Int, String) -> Unit) {
        val colors = this.split(",")
        colors.forEach {
            val (count, color) = it.trim().split(" ")
            operation(count.toInt(), color)
        }
    }
}