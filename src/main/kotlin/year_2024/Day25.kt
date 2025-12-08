package year_2024

typealias Lock = List<Int>
typealias Key = List<Int>

class Day25(private val input: String) {

    private fun getSignature(block: String): List<Int> {
        val signature = mutableListOf<Int>()
        val lines = block.lines()
        for (x in 0 until lines.first().length) {
            var count = 0
            for (y in 1 .. 5) {
                if (lines[y][x] == '#') count++
            }
            signature += count
        }
        return signature
    }

    fun transform(input: String): Pair<List<Lock>, List<Key>> {
        val blocks = input.split("\n\n")

        val (lBlocks, kBlocks) = blocks.partition { it[0] == '#' }
        val locks = lBlocks.map(::getSignature)
        val keys = kBlocks.map(::getSignature)

        return locks to keys
    }

    private fun match(key: Key, lock: Lock): Boolean {
        return (key zip lock).all { (keyPin, lockPin) -> keyPin + lockPin <= 5 }
    }

    fun solvePart1(): Int {
        val (locks, keys) = transform(input)
        return locks.sumOf { l -> keys.count { k -> match(k, l) } }
    }
}