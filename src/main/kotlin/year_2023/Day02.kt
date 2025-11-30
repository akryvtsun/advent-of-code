package year_2023

class Day02(private val input: List<String>) {

    private val limits = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14,
    )

    fun solvePart1(): Int =
        input.sumOf { line ->
            val (gameId, turns) = line.parseGame()

            val possible = turns.all { turn ->
                // every color count must be ≤ allowed limit
                turn.all { (color, count) ->
                    count <= limits.getValue(color)
                }
            }

            if (possible) gameId else 0
        }

    fun solvePart2(): Int =
        input.sumOf { line ->
            val (_, turns) = line.parseGame()

            // find max count per color across all turns
            val maxByColor = mutableMapOf(
                "red" to 0,
                "green" to 0,
                "blue" to 0,
            )

            turns.forEach { turn ->
                turn.forEach { (color, count) ->
                    maxByColor[color] = maxOf(maxByColor.getValue(color), count)
                }
            }

            // power = product of max red * max green * max blue
            maxByColor.values.reduce(Int::times)
        }

    /**
     * Parses a line into:
     *  - game id (Int)
     *  - list of turns; each turn is Map<color, count>
     */
    private fun String.parseGame(): Pair<Int, List<Map<String, Int>>> {
        val id = substringAfter("Game ")
            .substringBefore(":")
            .toInt()

        val turns = substringAfter(":")
            .split(";")
            .map { turn ->
                turn.split(",")
                    .associate { part ->
                        val (count, color) = part.trim().split(" ")
                        color to count.toInt()
                    }
            }

        return id to turns
    }
}