package year_2025

data class Point(val y: Int, val x: Int) {
    fun left() = copy(x = this.x - 1)
    fun right() = copy(x = this.x + 1)
    fun down() = copy(y = this.y + 1)
}

data class Surface(val lines: List<String>) {

    fun find(symbol: Char) = findAll(symbol).first()

    fun findAll(symbol: Char) = lines
        .withIndex()
        .flatMap { (index, line) ->
            line.mapIndexedNotNull { x, c -> if (c == symbol) Point(index, x) else null }
        }
        .toSet()

    operator fun contains(p: Point) =
        p.y in lines.indices &&
                p.x in lines.first().indices
}

fun <T> permutations(elements: List<T>) = sequence {
    val n = elements.size
    for (i in 0 until n - 1) {
        for (j in i + 1 until n) {
            yield(elements[i] to elements[j])
        }
    }
}