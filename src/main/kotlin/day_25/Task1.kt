package day_25

typealias Lock = List<Int>
typealias Key = List<Int>

class Task1 {

    companion object {

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

        private fun isLock(block: String) =
            block.substringBefore('\n').all { it == '#' }

        fun transform(input: String): Pair<List<Lock>, List<Key>> {
            val locks = mutableListOf<Lock>()
            val keys = mutableListOf<Key>()

            val blocks = input.split("\n\n")
            blocks.forEach { block ->
                val signature = getSignature(block)
                if (isLock(block))
                    locks += signature
                else
                    keys += signature
            }

            return Pair(locks, keys)
        }

        fun solve(locks: List<Lock>, keys: List<Key>): Long {
            return 0
        }
    }
}