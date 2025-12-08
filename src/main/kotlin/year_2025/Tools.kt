package year_2025

/**
 * 2D point representation
 */
data class Point(val y: Int, val x: Int) {
    fun left() = copy(x = this.x - 1)
    fun right() = copy(x = this.x + 1)
    fun down() = copy(y = this.y + 1)
}

/**
 * Map holder
 */
data class Surface(val lines: List<String>) {

    // finds initial puzzle position
    fun find(symbol: Char) = findAll(symbol).first()

    // finds all similar elements (walls, rolls etc.) Typically, there are obstacles.
    fun findAll(symbol: Char) = lines
        .withIndex()
        .flatMap { (index, line) ->
            line.mapIndexedNotNull { x, c -> if (c == symbol) Point(index, x) else null }
        }
        .toSet()

    // checks whether point is in map area
    operator fun contains(p: Point) =
        p.y in lines.indices &&
                p.x in lines.first().indices
}

/**
 * Creates sequence unique pairs (permutations]) built from provided list of elements
 */
fun <T> List<T>.pairs(): Sequence<Pair<T, T>> = sequence {
    for (i in indices) {
        for (j in i + 1 until size) {
            yield(get(i) to get(j))
        }
    }
}