package year_2023

class Day02(private val input: List<String>) {

    fun solvePart1() =
        input
            .sumOf { game ->
                val number = game.substringBefore(":").substringAfter(" ").toInt()
                val turns = game.substringAfter(":").split(";")
                if (turns.all { it.isPossible() }) number else 0
            }

    fun solvePart2(): Int {
        return input
            .sumOf { game ->
                val minSetup = mutableMapOf(
                    "red" to 0,
                    "green" to 0,
                    "blue" to 0,
                )
                val turns = game.substringAfter(":").split(";")
                turns.forEach { turn ->
                    val colors = turn.split(",")
                    colors.forEach { color ->
                        val (count, color) = color.trim().split(" ")
                        minSetup[color] = maxOf(minSetup[color]!!, count.toInt())
                    }
                }
                minSetup.values.reduce { acc, set -> acc * set }
            }
    }

    val setup = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14,
    )

    private fun String.isPossible(): Boolean {
        val colors = this.split(",")
        return colors.all {
            val (count, color) = it.trim().split(" ")
            setup[color]!! >= count.toInt()
        }
    }
}