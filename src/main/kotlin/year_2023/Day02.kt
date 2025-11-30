package year_2023

class Day02(private val input: List<String>) {

    fun solvePart1(): Int {
        return input
            .sumOf { game ->
                val number = game.substringBefore(":").substringAfter(" ").toInt()
                val turns = game.substringAfter(":").split(";")
                if (turns.all { it.isPossible() }) number else 0
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